# 代码来源
# https://blog.csdn.net/qq_34874784/article/details/108075428?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522165933981616781685339660%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=165933981616781685339660&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduend~default-2-108075428-null-null.142^v37^new_blog_pos_by_title&utm_term=python%20%E8%A7%86%E9%A2%91%E5%8E%8B%E7%BC%A9&spm=1018.2226.3001.4187
# 视频压缩第二版
import os
import platform
import threading
import sys


# import ffmpeg 安装工具

class Compress_Pic_or_Video(object):
    def __init__(self, filePath, inputName):
        # outName = "new_" + inputName
        # outName = inputName  #不知道怎么的，ffmpeg不能重写原文件 只能放一个新的了
        outName = "new_" + inputName
        self.filePath = filePath  # 文件地址
        self.inputName = inputName  # 输入的文件名字
        self.outName = outName  # 输出的文件名字
        self.system_ = platform.platform().split("-", 1)[0]
        if self.system_ == "Windows":
            self.filePath = (self.filePath + "\\") if self.filePath.rsplit("\\", 1)[-1] else self.filePath
        elif self.system_ == "Linux":
            self.filePath = (self.filePath + "/") if self.filePath.rsplit("/", 1)[-1] else self.filePath
        self.fileInputPath = self.filePath + inputName
        self.fileOutPath = self.filePath + outName

    @property
    def is_video(self):
        videoSuffixSet = {"WMV", "ASF", "ASX", "RM", "RMVB", "MP4", "3GP", "MOV", "M4V", "AVI", "DAT", "MKV", "FIV",
                          "VOB"}
        suffix = self.fileInputPath.rsplit(".", 1)[-1].upper()
        if suffix in videoSuffixSet:
            return True
        else:
            return False

    def SaveVideo(self):
        fpsize = os.path.getsize(self.fileInputPath) / 1024
        if fpsize >= 150.0:  # 大于150KB的视频需要压缩
            if self.outName:
                compress = "ffmpeg -i {} -r 10 -pix_fmt yuv420p -vcodec libx264 -preset veryslow -profile:v baseline  -crf 23 -acodec aac -b:a 32k -strict -5 {}".format(
                    self.fileInputPath, self.fileOutPath)
                isRun = os.system(compress)
            else:
                compress = "ffmpeg -i {} -r 10 -pix_fmt yuv420p -vcodec libx264 -preset veryslow -profile:v baseline  -crf 23 -acodec aac -b:a 32k -strict -5 {}".format(
                    self.fileInputPath, self.fileInputPath)
                isRun = os.system(compress)
            if isRun != 0:
                return (isRun, "没有安装ffmpeg")
            return True
        else:
            return True

    def Compress_Video(self):
        # 异步保存打开下面的代码，注释同步保存的代码
        thr = threading.Thread(target=self.SaveVideo)
        thr.start()
        # 下面为同步代码
        # fpsize = os.path.getsize(self.fileInputPath) / 1024
        # if fpsize >= 150.0:  # 大于150KB的视频需要压缩
        #     compress = "ffmpeg -i {} -r 10 -pix_fmt yuv420p -vcodec libx264 -preset veryslow -profile:v baseline  -crf 23 -acodec aac -b:a 32k -strict -5 {}".format(
        #         self.fileInputPath, self.fileOutPath)
        #     isRun = os.system(compress)
        #     if isRun != 0:
        #         return (isRun, "没有安装ffmpeg")
        #     return True
        # else:
        #     return True


if __name__ == "__main__":
    [filePath, inputName] = sys.argv[1:]  # 测试压缩
    savevideo = Compress_Pic_or_Video(filePath, inputName);
    print(savevideo.Compress_Video())

# 这一版性能优良  压缩 从61M 到 11M 视频看起来没有太大损伤  缺点：inteli7 8G运存 耗时20s
