import requests
from bs4 import BeautifulSoup
import datetime
from pytz import timezone
from config import Config


def parseDateTime(dt: str, dtzone: str, fmt: str) -> datetime.datetime:
    tz1 = timezone(dtzone)  # Contest Zone
    tz2 = timezone(Config.zoneID)  # Local Zone

    # parsing datetime
    dt = datetime.datetime.strptime(dt, fmt)
    # converting to UTC
    dt = tz1.localize(dt)
    # Convert zone
    return dt.astimezone(tz2)


class MainScraper:
    soup = None
    month = {
        'Jan': 1,
        'Feb': 2,
        'Mar': 3,
        'Apr': 4,
        'May': 5,
        'Jun': 6,
        'Jul': 7,
        'Aug': 8,
        'Sep': 9,
        'Oct': 10,
        'Nov': 11,
        'Dec': 12
    }

    def __init__(self, url):
        self.url = url

    def init(self):
        self.soup = self.getSoup()

    def getSoup(self):
        return BeautifulSoup(requests.get(self.url).content, 'lxml')
