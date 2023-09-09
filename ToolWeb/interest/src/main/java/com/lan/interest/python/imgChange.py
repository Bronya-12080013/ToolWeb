import cv2
import sys

if __name__ == '__main__':
    imgPath = sys.argv[1]
    savePath = sys.argv[2]
    flags = int(sys.argv[3])
    image = cv2.imread(imgPath, flags)
    cv2.imwrite(savePath, image)  # 保存图像
