import json

import requests


class MainAPIParser:
    def __init__(self, url):
        self.url = url

    def fetch(self):
        return json.loads(requests.get(self.url).content)
