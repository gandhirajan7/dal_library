import json
import urllib.parse
import boto3
import csv
import codecs
import sys
import logging
import rds_config
import pymysql
import requests

def insert_into_db(book_data):
    try:
        for index,line in enumerate(book_data):
            if(index!=0):
                isbn = line[1]
                book_name= line[2]
                author = line[3]
                year_of_publication = line[4]
                genre = line[5]
                request_body = {'name':book_name, 'author': author, 'genre': genre}
                res = requests.post('http://lmsbackendv2-env.eba-ybvchve3.us-east-1.elasticbeanstalk.com/books/save', json=request_body)
                print(res)
    except Exception as e:
        print(e)
        print('Error getting object {} from bucket {}. Make sure they exist and your bucket is in the same region as this function.'.format(key, bucket))
    print("after try")
    return 'Hello'


def lambda_handler(event, context):
    #print("Received event: " + json.dumps(event, indent=2))
    s3 = boto3.client('s3')
    # Get the object from the event and show its content type
    bucket = event['Records'][0]['s3']['bucket']['name']
    key = urllib.parse.unquote_plus(event['Records'][0]['s3']['object']['key'], encoding='utf-8')
    print("test")
    try:
        print(bucket)
        print(key)
        response = s3.get_object(Bucket=bucket, Key=key)
        
        byte_content = response['Body'].read().decode('utf-8').split('\n')
        csv_data = csv.reader(byte_content)
        insert_into_db(csv_data)
        for index, line in enumerate(csv_data):
            if index != 0:
                isbn = line[1]
                book_name = line[2]
                author = line[3]
                year_of_publication = line[4]
                genre = line[5]
                print(book_name)
        print("CONTENT TYPE: " + response['ContentType'])
        return response['ContentType']
    except Exception as e:
        print(e)
        print('Error getting object {} from bucket {}. Make sure they exist and your bucket is in the same region as this function.'.format(key, bucket))
        raise e
