from scraper.MainAPIParser import MainAPIParser


class CodeChef(MainAPIParser):
    url = 'https://www.codechef.com/api/list/contests/all?sort_by=START&sorting_order=asc&offset=0&mode=premium'

    def __init__(self):
        super().__init__(self.url)

    def get_upcoming(self):
        return self.fetch()['future_contests']

    def get_ongoing(self):
        return self.fetch()['present_contests']
