#!/usr/bin/env python3
# -*- coding: utf-8 -*-
# find the line of containing chinese in files
__author__ = 'YR'
import re

def flow_control():
    source_filename = input("请输入待筛选文件名（带文件后缀）：")
    if source_filename == "":
        source_filename = "all_contact.txt"

    filter_input = input("是否进行关键词搜索？（Y/N）：")
    filter_flag = True if filter_input == "Y" else False
    if filter_flag:
        print("执行关键词搜索，默认关键词字典名： dict.txt")
        try:
            with open("dict.txt", 'rb') as filter_file:
                #chinese_count = start_find_chinese(source_file)
                print("结果->关键词命中情况如下：", chinese_count)
        except FileNotFoundError:
            with open("dict.txt", 'w'):
                input("ERROR:字典 dict.txt不存在，已创建，请将关键词复制入该文件！")
    return source_filename

def start_find_chinese(source_file):
    chinese_count = 0;
    with open('筛选结果.txt', 'wb') as outfile:
        while True:
            content_chinese = source_file.readline()
            if re.match(r'(.*[\u4E00-\u9FA5]+)|([\u4E00-\u9FA5]+.*)', content_chinese.decode('utf-8')):
                outfile.write(content_chinese)
                chinese_count += 1;
            if not content_chinese:
                return chinese_count

# start to find
if __name__ == '__main__':
    chinese_count = 0
    filter_count = 0

    source_filename = flow_control()

    try:
        with open(source_filename, 'rb') as source_file:
            chinese_count = start_find_chinese(source_file)
            print("\n结果->汉字筛选结束，含汉字行数：", chinese_count)
    except FileNotFoundError:
        input("ERROR:未找到源文件： " + source_filename + "，请检查当前路径下有无该文件！")

    input('请到"筛选结果.txt"中查看所有命中行，回车键退出...')


