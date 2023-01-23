from config import Config
from scraper.AtCoder import AtCoder
from scraper.CodeChef import CodeChef
from scraper.CodeForces import CodeForces


def load_oj():
    Config.oj = {
        'cf': CodeForces(),
        'cc': CodeChef(),
        'ac': AtCoder(),
    }


def init_args(argv):
    Config.senderEmail = argv[1]
    Config.senderPassword = argv[2]
    Config.receiverEmail = argv[3]
    Config.userOJ = argv[4].strip().split(',')
    Config.zoneID = argv[5]


def load_styles():
    f = open(Config.style_path)
    for line in f.readlines():
        parts = line.split('@@')
        Config.styles[parts[0]] = parts[1]
    f.close()


def init(argv):
    load_styles()
    load_oj()
    init_args(argv)
