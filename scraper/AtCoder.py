from scraper.MainScraper import MainScraper


class AtCoder(MainScraper):
    url = 'https://atcoder.jp/contests/'

    def __init__(self):
        super().__init__(self.url)

    def getContests(self):
        contests = []
        table = self.soup.findAll('table')[2]
        rows = table.find('tbody').findAll('tr')

        for row in rows:
            contest = []
            row = row.findAll('td')
            for td in row:
                contest.append(td.get_text().strip())
            contests.append(contest)

        return contests
