#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Dec 21 12:50:53 2021

@author: blue
"""
sum = 0
three = 0
six = 0
nine = 0
for i in range(1,1000000):
    sum += 1/i
    if sum > 3 and three == 0:
        three = i
        print(sum, i)
    if sum > 6 and six == 0:
        six = i
        print(sum, i)
    if sum > 9 and nine == 0:
        nine = i
        print(sum, i)
        break;
    