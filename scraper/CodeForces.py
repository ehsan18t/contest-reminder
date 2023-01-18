from scraper.MainScraper import MainScraper


class CodeForces(MainScraper):
    url = 'https://codeforces.com/contests'

    def __init__(self):
        super().__init__(self.url)

    def getContests(self):
        contests = []
        table = self.soup.find('table')
        rows = table.findAll('tr')[1:]  # Excluding Table header

        for row in rows:
            contest = []
            row = row.findAll('td')
            row.pop(1)     # Excluding Writers Name
            for td in row:
                contest.append(td.get_text().strip())
            contests.append(contest)

        return contests
