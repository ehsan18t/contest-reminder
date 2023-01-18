from scraper.CodeForces import CodeForces

if __name__ == '__main__':
    cf = CodeForces()
    cf.init()
    for ll in cf.getContests():
        print(ll)
