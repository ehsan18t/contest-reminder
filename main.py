import sys

from config import init


# for debug
def write_to_html(text):
    f = open('index.html', 'w')
    f.write(text)
    f.close()


def main():
    init.init(sys.argv)


if __name__ == '__main__':
    main()
