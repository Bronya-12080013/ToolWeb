import os
import cv2
import sys


def fun(image_path, saveMp4_path,fbs):
    # 要被合成的多张图片所在文件夹
    # 路径分隔符最好使用“/”,而不是“\”,“\”本身有转义的意思；或者“\\”也可以。
    # 因为是文件夹，所以最后还要有一个“/”
    file_dir = image_path
    list = []
    """
    原方法
    遍历出来的files是文件名列表 如"1.jpg","2.jpg"
    但是得出的files顺序不是如我想要的以自然数名称排序
    所以我自己写了
    我有我的程序，知道文件的命名方式，按自己所需写就行
    for root, dirs, files in os.walk(file_dir):
        for i in range(0, len(files)):
            list.append(files[i])  # 获取目录下文件名列表
    """
    # 特适应所需
    for root, dirs, files in os.walk(file_dir):
        for i in range(1, len(files)+1):
            #  list.append(i+'.jpg') python不能直接拼?
            list.append(str(i)+'.jpg')

    # VideoWriter是cv2库提供的视频保存方法，将合成的视频保存到该路径中
    # 'MJPG'意思是支持jpg格式图片
    # fps = 5代表视频的帧频为5，如果图片不多，帧频最好设置的小一点
    # (1280,720)是生成的视频像素1280*720，一般要与所使用的图片像素大小一致，否则生成的视频无法播放
    # 定义保存视频目录名称和压缩格式，像素为1280*720
    # 像素要设置得与图片相同!!!
    video = cv2.VideoWriter(saveMp4_path, cv2.VideoWriter_fourcc('m', 'p', '4', 'v'), fbs, (1920, 1080))

    for i in range(0, len(list)):
        img = cv2.imread(image_path + list[i])  # 读取图片
        # print(image_path + list[i])
        # img = cv2.resize(img,(1981,991)) #将图片转换为1280*720像素大小
        video.write(img)  # 写入视频

    # 释放资源
    video.release()


if __name__ == '__main__':
    print("[syn_mp4.py]")
    image_path = sys.argv[1]
    saveMp4_path = sys.argv[2]
    # 这里犯傻了 本来就是以String传进来的 应该放double的
    # 可以用终端直接输python命令 可以看到报错
    # fbs = str(sys.argv[3])
    # fbs = double(sys.argv[3]) 不存在这样的方法
    fbs = int(sys.argv[3])
    fun(image_path, saveMp4_path,fbs)
