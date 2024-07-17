package kr.goldenmine.baekjoon.bronze.bronze1.p19564;

interface Main{static void main(String[]args)throws Exception{byte[]b=new byte[100001];int N=System.in.read(b)-2,n=1,i=0;for(;i<N;++i){if(b[i]>=b[i+1])++n;}System.out.print(n);}}
