#!/bin/bash

echo "Signing up a new user..."
curl -X POST --data "name=Test User&email=testuser@example.com&phone=1234567890&password=testuser" http://localhost:8080/signup
echo ""
echo ""

echo "Logging in the new user..."
curl -X POST -c cookie.txt --data "email=testuser@example.com&password=testuser" http://localhost:8080/login
echo ""
echo ""

echo "Adding a new website for the user..."
curl -X POST -b cookie.txt --data "name=Example&url=http://www.example.com&notifyDown=notifyDown&notifyResponse=notifyResponse" http://localhost:8080/webmon/websites
echo ""
echo ""

echo "Logging in a previous user with data..."
curl -X POST -c cookie.txt --data "email=sampleuser@example.com&password=sample" http://localhost:8080/login
echo ""
echo ""

echo "Getting current user details..."
curl -X GET -H "accept: application/json" -b cookie.txt http://localhost:8080/webmon/users/1
echo ""
echo ""

echo "Getting details of one of the websites being monitored..."
curl -X GET -H "accept: application/json" -b cookie.txt http://localhost:8080/webmon/websites/1
echo ""
echo ""

echo "Trying to get details of a non-existant website or a website this user doesn't have access to (must return error page)..."
curl -X GET -b cookie.txt http://localhost:8080/webmon/websites/10
echo ""
echo ""
