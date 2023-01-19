import sys
from config import Config
from config import init


# for debug
def writeToHtml(text):
    f = open('index.html', 'w')
    f.write(text)
    f.close()


def main():
    init.init(sys.argv)


if __name__ == '__main__':
    main()
