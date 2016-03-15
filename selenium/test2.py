import unittest
import time
from selenium import webdriver
from selenium.webdriver.common.keys import Keys

class WebMonTest(unittest.TestCase):

    def setUp(self):
        self.driver = webdriver.Firefox()

    def test1(self):
        driver = self.driver
        driver.get("http://cs263-webmon.appspot.com/login")
        email = driver.find_element_by_id("email")
        password = driver.find_element_by_id("password")
        
        # Login with valid id, test login functionality
        email.send_keys("darshanmaiya@outlook.co.in")
        password.send_keys("darshan")
        driver.find_element_by_id("submit").click()
        time.sleep(3)
        
        # Test adding website
        text = driver.find_element_by_id("addWebsite").click()
        time.sleep(3)

        name = driver.find_element_by_id("name")
        url = driver.find_element_by_id("url")
        name.send_keys("CS UCSB")
        url.send_keys("http://cs.ucsb.edu")
        driver.find_element_by_id("submit").click()

    def tearDown(self):
        self.driver.close()

if __name__ == "__main__":
    unittest.main()
