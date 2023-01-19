from scraper.CodeChef import CodeChef
from scraper.CodeForces import CodeForces

if __name__ == '__main__':
    # CodeForces Example
    cf = CodeForces()
    cf.init()
    for ll in cf.getContests():
        print(ll)
    # CodeChef Example
    cc = CodeChef()
    for c in cc.fetch()['future_contests']:
        print(c['contest_name'])
