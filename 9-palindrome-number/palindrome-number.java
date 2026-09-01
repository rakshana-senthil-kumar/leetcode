class Solution {
    public boolean isPalindrome(int x) {
        int rev=0;
        int temp;
        temp=x;
        
        while(temp>0){
            int digit = temp%10;
            rev=rev*10+digit;
            temp=temp/10;
            
        }
        return x==rev;
        
    }
}