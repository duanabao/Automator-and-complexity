#include <iostream>
using namespace std;

void main()
{
	char c[3];
	cout << "请输入月份： "<<;
	cin >>c;
	if(isDay(c)) cout << endl <<"是合法月份！"<<endl;
	else cout <<endl << "不是合法月份！"<<endl;
}

bool isDay(char *p)
{
	bool isDayFlag = false;
	if(p[0]=='1') 
	{
		isDayFlag = true;
		switch (p[1])
		{
		case 0:
		case 1:
		case 2: 
		}
}