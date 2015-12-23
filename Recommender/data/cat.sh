#!/bin/bash
catf(){
#cd /Users/samluo/Downloads/download/
cat temp.dat $1 > recc.dat
}

clean(){
	#cd /Users/samluo/Downloads/download/
	rm -f temp.dat recc.dat
}
while getopts b:c cho; do
    case $cho in
	b) catf $OPTARG
	    ;;
	c) clean
	    ;;
    esac
done