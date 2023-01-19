from scraper.MainScraper import MainScraper, parseDateTime


class CodeForces(MainScraper):
    url = 'https://codeforces.com/contests'
    ojZone = 'Europe/Moscow'

    def __init__(self):
        super().__init__(self.url)

    def getContests(self):
        self.init()
        contests = []
        table = self.soup.find('table')
        rows = table.findAll('tr')[1:]  # Excluding Table header

        for row in rows:
            contest = []
            row = row.findAll('td')
            row.pop(1)  # Excluding Writers Name
            for td in row:
                contest.append(td.get_text().strip())
            contest[1] = parseDateTime(contest[1], self.ojZone, '%b/%d/%Y %H:%M')
            contest[1] = contest[1].strftime('%Y-%m-%d %H:%M')
            contests.append(contest)

        return contests
