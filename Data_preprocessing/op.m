clear
M = csvread('0.txt');
B = sortrows(M);
opfile = fopen('r0.txt','w');
[r,c] = size(B);
for i=1:r
    fprintf(opfile,'%d,%d,%d\n',B(i,:));
end

clear
M = csvread('1.txt');
B = sortrows(M);
opfile = fopen('r1.txt','w');
[r,c] = size(B);
for i=1:r
    fprintf(opfile,'%d,%d,%d\n',B(i,:));
end

clear
M = csvread('2.txt');
B = sortrows(M);
opfile = fopen('r2.txt','w');
[r,c] = size(B);
for i=1:r
    fprintf(opfile,'%d,%d,%d\n',B(i,:));
end

clear
M = csvread('3.txt');
B = sortrows(M);
opfile = fopen('r3.txt','w');
[r,c] = size(B);
for i=1:r
    fprintf(opfile,'%d,%d,%d\n',B(i,:));
end

clear
M = csvread('4.txt');
B = sortrows(M);
opfile = fopen('r4.txt','w');
[r,c] = size(B);
for i=1:r
    fprintf(opfile,'%d,%d,%d\n',B(i,:));
end

clear
M = csvread('5.txt');
B = sortrows(M);
opfile = fopen('r5.txt','w');
[r,c] = size(B);
for i=1:r
    fprintf(opfile,'%d,%d,%d\n',B(i,:));
end

clear
M = csvread('6.txt');
B = sortrows(M);
opfile = fopen('r6.txt','w');
[r,c] = size(B);
for i=1:r
    fprintf(opfile,'%d,%d,%d\n',B(i,:));
end

clear
M = csvread('7.txt');
B = sortrows(M);
opfile = fopen('r7.txt','w');
[r,c] = size(B);
for i=1:r
    fprintf(opfile,'%d,%d,%d\n',B(i,:));
end

clear
M = csvread('8.txt');
B = sortrows(M);
opfile = fopen('r8.txt','w');
[r,c] = size(B);
for i=1:r
    fprintf(opfile,'%d,%d,%d\n',B(i,:));
end

clear
M = csvread('9.txt');
B = sortrows(M);
opfile = fopen('r9.txt','w');
[r,c] = size(B);
for i=1:r
    fprintf(opfile,'%d,%d,%d\n',B(i,:));
end