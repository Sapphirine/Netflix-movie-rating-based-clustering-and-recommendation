#!/bin/bash
catf(){
cat temp.dat $1 > data/recc.dat
}

clean(){
	rm -f data/temp.dat data/recc.dat
}
while getopts b:c cho; do
    case $cho in
	b) catf $OPTARG
	    ;;
	c) clean
	    ;;
    esac
done
