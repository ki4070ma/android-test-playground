#!/bin/sh

function start_sample_app(){
    adb shell am start -n amori.rssapp/.MainActivity
}