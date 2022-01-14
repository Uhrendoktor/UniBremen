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

function S(N){
    let sum = 0;
    for(let n = 0; n <= N; n++){
        let psum = 1;
        for(let k = n; k > 0 ; k--){
            psum*=(-100)/k;
        }
        sum+=psum;
    }
    return sum;
}

//9.5a
let maxN = [0], max = 0;
for(let N = 0; N < 100000; N++){
    let SN = S(N);
    if(SN>max){
        maxN = [N];
        max = SN;
    }
    else if(SN==max){
        maxN.push(N);
    }
}

//9.5b
for(let N = 0; N < 1000; N++){
    if((S(N)*10e44).toFixed(2)==3.72){
        console.log(N);
        break;
    }
}
