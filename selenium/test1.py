import unittest
import time
from selenium import webdriver
from selenium.webdriver.common.keys import Keys

class WebMonTest(unittest.TestCase):

    def setUp(self):
        self.driver = webdriver.Firefox()

    def test1(self):
        driver = self.driver
        driver.get("http://cs263-webmon.appspot.com/signup")
        name = driver.find_element_by_id("name")
        email = driver.find_element_by_id("email")
        phone = driver.find_element_by_id("phone")
        password = driver.find_element_by_id("password")
        repassword = driver.find_element_by_id("repassword")
       
        name.send_keys("Tester1")
        email.send_keys("tester1gmail.com")
        phone.send_keys("1234567890")
        password.send_keys("tester1")
        repassword.send_keys("tester1")
        time.sleep(2)
        driver.find_element_by_id("submit").click()

        time.sleep(5)
    
    def tearDown(self):
        self.driver.close()

if __name__ == "__main__":
    unittest.main()
