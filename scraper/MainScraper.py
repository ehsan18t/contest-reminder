import requests
from bs4 import BeautifulSoup


class MainScraper:
    soup = None

    def __init__(self, url):
        self.url = url

    def init(self):
        self.soup = self.getSoup()

    def getSoup(self):
        return BeautifulSoup(requests.get(self.url).content, 'lxml')
