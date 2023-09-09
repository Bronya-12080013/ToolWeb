import cv2
import sys


def fun(mp4Path, imgPath):
    cap = cv2.VideoCapture(mp4Path)
    index = 0
    while cap.isOpened():
        index = index + 1
        ret, frame = cap.read()
        gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
        ret, binary = cv2.threshold(gray, 255, 255, 255)  # 自己调参 我觉得这样挺好的
        canny = cv2.Canny(binary, 50, 100)

        # 显示
        # 调整窗口大小 注意名字要一样
        cv2.namedWindow("video", 0)
        cv2.resizeWindow("video", 923, 640)
        cv2.imshow("video", canny)

        cv2.imwrite(imgPath + str(index) + '.jpg', canny)
        if cv2.waitKey(1) == ord('q'):
            break
    cap.release()
    cv2.destroyAllWindows()


if __name__ == '__main__':
    print("[Edge_detection.py]")
    mp4Path = sys.argv[1]
    imgPath = sys.argv[2]
    fun(mp4Path, imgPath)
