function mymesh()
temdat=load('C:\Users\Tianchun\Desktop\big data\final_proj_bigdata\weight_final\for_mat.txt');
cluster=temdat(:,1);
year=temdat(:,2);
avg=temdat(:,3);
total=temdat(:,4);

i1=1;
year1=[];
avg1=[];
total1=[];

i2=1;
year2=[]
avg2=[]
total2=[]

i3=1;
year3=[]
avg3=[]
total3=[]

i4=1;
year4=[]
avg4=[]
total4=[]

i5=1;
year5=[]
avg5=[]
total5=[]

i6=1;
year6=[]
avg6=[]
total6=[]

i7=1;
year7=[]
avg7=[]
total7=[]

i8=1;
year8=[]
avg8=[]
total8=[]

i9=1;
year9=[]
avg9=[]
total9=[]

i10=1;
year10=[]
avg10=[]
total10=[]

i11=1;
year11=[]
avg11=[]
total11=[]

i12=1;
year12=[]
avg12=[]
total12=[]

i13=1;
year13=[]
avg13=[]
total13=[]

i14=1;
year14=[]
avg14=[]
total14=[]

i15=1;
year15=[]
avg15=[]
total15=[]

i16=1;
year16=[]
avg16=[]
total16=[]

i17=1;
year17=[]
avg17=[]
total17=[]

i18=1;
year18=[]
avg18=[]
total18=[]

i19=1;
year19=[]
avg19=[]
total19=[]

i20=1;
year20=[]
avg20=[]
total20=[]

for n=1:17770
    if cluster(n)==1
        year1(i1)=year(n)
        avg1(i1)=avg(n)
        total1(i1)=total(n)
        i1=i1+1
    end
    if cluster(n)==2
        year2(i2)=year(n)
        avg2(i2)=avg(n)
        total2(i2)=total(n)
        i2=i2+1
    end
    if cluster(n)==3
        year3(i3)=year(n)
        avg3(i3)=avg(n)
        total3(i3)=total(n)
        i3=i3+1
    end
    if cluster(n)==4
        year4(i4)=year(n)
        avg4(i4)=avg(n)
        total4(i4)=total(n)
        i4=i4+1
    end
    if cluster(n)==5
        year5(i5)=year(n)
        avg5(i5)=avg(n)
        total5(i5)=total(n)
        i5=i5+1
    end
    if cluster(n)==6
        year6(i6)=year(n)
        avg6(i6)=avg(n)
        total6(i6)=total(n)
        i6=i6+1
    end
    if cluster(n)==7
        year7(i7)=year(n)
        avg7(i7)=avg(n)
        total7(i7)=total(n)
        i7=i7+1
    end
    if cluster(n)==8
        year8(i8)=year(n)
        avg8(i8)=avg(n)
        total8(i8)=total(n)
        i8=i8+1
    end
    if cluster(n)==9
        year9(i9)=year(n)
        avg9(i9)=avg(n)
        total9(i9)=total(n)
        i9=i9+1
    end
    if cluster(n)==10
        year10(i10)=year(n)
        avg10(i10)=avg(n)
        total10(i10)=total(n)
        i10=i10+1
    end
    if cluster(n)==11
        year11(i11)=year(n)
        avg11(i11)=avg(n)
        total11(i11)=total(n)
        i11=i11+1
    end
    if cluster(n)==12
        year12(i12)=year(n)
        avg12(i12)=avg(n)
        total12(i12)=total(n)
        i12=i12+1
    end
    if cluster(n)==13
        year13(i13)=year(n)
        avg13(i13)=avg(n)
        total13(i13)=total(n)
        i13=i13+1
    end
    if cluster(n)==14
        year14(i14)=year(n)
        avg14(i14)=avg(n)
        total14(i14)=total(n)
        i14=i14+1
    end
    if cluster(n)==15
        year15(i15)=year(n)
        avg15(i15)=avg(n)
        total15(i15)=total(n)
        i15=i15+1
    end
    if cluster(n)==16
        year16(i16)=year(n)
        avg16(i16)=avg(n)
        total16(i16)=total(n)
        i16=i16+1
    end
    if cluster(n)==17
        year17(i17)=year(n)
        avg17(i17)=avg(n)
        total17(i17)=total(n)
        i17=i17+1
    end
    if cluster(n)==18
        year18(i18)=year(n)
        avg18(i18)=avg(n)
        total18(i18)=total(n)
        i18=i18+1
    end
    if cluster(n)==19
        year19(i19)=year(n)
        avg19(i19)=avg(n)
        total19(i19)=total(n)
        i19=i19+1
    end
    if cluster(n)==20
        year20(i20)=year(n)
        avg20(i20)=avg(n)
        total20(i20)=total(n)
        i20=i20+1
    end
end

figure;
h=plot3(year1,avg1,total1,'.','Color',[0.5 0.5 0.5])
hold on
h2=plot3(year2,avg2,total2,'.','Color',[0.9,0.9,0.9])
hold on
h3=plot3(year3,avg3,total3,'.','Color',[0.9,0.5,0.2])
hold on
h4=plot3(year4,avg4,total4,'.','Color',[0.2,0.2,0.9])
hold on
h5=plot3(year5,avg5,total5,'.','Color',[0.1,0.3,0.3])
hold on
h6=plot3(year6,avg6,total6,'.','Color',[0.2,0.9,0.2])
hold on
h7=plot3(year7,avg7,total7,'.','Color',[0.7,0.5,0.8])
hold on
h8=plot3(year8,avg8,total8,'k.')
hold on
h9=plot3(year9,avg9,total9,'.','Color',[0.9,0.7,0.7])
hold on
h10=plot3(year10,avg10,total10,'b.')
hold on
h11=plot3(year11,avg11,total11,'.','Color',[0.9,0.8,0.4])
hold on
h12=plot3(year12,avg12,total12,'m.')
hold on
h13=plot3(year13,avg13,total13,'.','Color',[0.3,0.7,0.9])
hold on
h14=plot3(year14,avg14,total14,'.','Color',[0.8,0.5,0.9])
hold on
h15=plot3(year15,avg15,total15,'.','Color',[0.99,0.5,0.5])
hold on
h16=plot3(year16,avg16,total16,'y.')
hold on
h17=plot3(year17,avg17,total17,'c.')
hold on
h18=plot3(year18,avg18,total18,'g.')
hold on
h19=plot3(year19,avg19,total19,'r.')
hold on
h20=plot3(year20,avg20,total20,'.','Color',[0.1,0.5,0.5])