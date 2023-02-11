
#include <iostream>
#include <vector>
#include <set>
#include <unordered_set>
using namespace std;
int StringCount(string str)
{
    unordered_set<char> s;
    for (int i = 0; i < str.size(); i++) {
        s.insert(str[i]);
    }
    return s.size();
}
int main()
{
    int t;
    cin >> t;
    while (t--)
    {
        int n,flag=0;
        string s;
        cin >> n >> s;
        string f1="", f2 = "";
        f1.push_back(s[0]);
        for (int i = 1; i < n-1; i++)
        {
            for (int j = 0; j < f1.size(); j++)
            {
                if (s[i] == f1[j]) {
                    flag = 1;
                    break;
                }
            }
            if (flag==1)break;
            else
                f1.push_back(s[i]);
        }
        for (int i = f1.size(); i < n; i++)f2.push_back(s[i]);
        cout << StringCount(f1) + StringCount(f2)<<endl;
    }
}
