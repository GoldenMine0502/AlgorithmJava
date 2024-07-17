package kr.goldenmine.baekjoon.bronze.bronze1.p19564;

interface Main{static void main(String[]args)throws Exception{byte[]b=new byte[100001];int N=System.in.read(b)-1,n=0,i=0;for(;i<N;++i){while(b[i]!=n%26+97)++n;++n;}System.out.println((n+25)/26);}}
