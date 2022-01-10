//9.5a
function fac(n){
    if(n<=1) return 1;
    return n*fac(n-1);
}

function S(N){
    let sum = 0;
    for(let n = 0; n <= N; n++){
        sum+=Math.pow(-100,n)/fac(n);
    }
    return sum;
}

let maxN = [0], max = 0;
for(let N = 0; N < 1000; N++){
    let SN = S(N);
    if(SN>max){
        maxN = [N];
        max = SN;
        console.log(N);
    }
    else if(SN==max){
        maxN.push(SN);
        console.log(N);
    }
}
//