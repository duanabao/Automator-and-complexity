#include <iostream>
using namespace std;

void main()
{
	char c[3];
	cout << "�������·ݣ� "<<;
	cin >>c;
	if(isDay(c)) cout << endl <<"�ǺϷ��·ݣ�"<<endl;
	else cout <<endl << "���ǺϷ��·ݣ�"<<endl;
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