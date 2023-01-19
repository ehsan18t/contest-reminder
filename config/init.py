import Config


def loadStyles():
    f = open(Config.style_path)
    for line in f.readlines():
        parts = line.split('@@')
        Config.styles[parts[0]] = parts[1]
    f.close()


def init():
    loadStyles()
