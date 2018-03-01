#include <iostream>
#include <Windows.h>
#include <time.h>

using namespace std;

COORD coord = { 0, 0 };

void gotoxy(int x, int y)
{
	coord.X = x;
	coord.Y = y;

	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), coord);
}

void Border()
{
	for (int a = 1; a <= 30; a++)
		{
			for (int b = 1; b <= 40; b++)
				{
					if (a == 1 || a == 30)
						{
							if (b == 1 || b % 5 == 0)
								cout << ":";
							else if (b % 2 == 0)
								cout << ".";
							else if (b % 3 == 0)
								cout << "_";
							else
							cout << "*";
						}
					else if (b == 1 || b == 40)
						cout << ".";
					else
						cout << " ";
				}

			cout << endl;
		}
}

void Tree(int x, int y)
{
	for (int i = 1; i <= 10; i++)
		{
			gotoxy(x, y + i);

			for (int j = 1; j <= 10 - i; j++)
				cout << " ";

			for (int k = 1; k <= i * 2 - 1; k++)
				cout << "*";
		}

 for (int l = 11; l<16; l++)
	{
		gotoxy(x + 7, y + l);

		for (int m = 0; m<5; m++)
			{
				if (m == 0 || m == 4)
					cout << "|";
				else
					cout << " ";
			}
	}
}

void Gift(int x, int y)
{
	for (int i = 0; i<3; i++)
		{
			gotoxy(x, y + i);

			for (int j = 0; j <= 6; j++)
				{
					if (i == 0 && j == 2)
						cout << "{";
					else if (i == 0 && j == 4)
						cout << "}";
					else  if ((j == 0 || j == 6) && (i == 1 || i == 2))
						cout << "|";
					else if ((i == 1) || (i == 0 && (j == 0 || j == 6)))
						cout << " ";
					else
						cout << "_";
				}
		}
}

void Snowman(int x, int y)
{
	for (int i = 0; i<5; i++)
		{
			gotoxy(x, y + i);
			for (int j = 0; j<7; j++)
				{
					if (i == 1 && (j == 2 || j == 4))
						cout << "|";
					else if (i == 2 && j == 3)
						cout << "\"";
					else if (((i == 2 || i == 3) && j == 2) || (i == 4 && j == 1))
						cout << "(";
					else if (((i == 2 || i == 3) && j == 4) || (i == 4 && j == 5))
						cout << ")";
					else if ((i == 0 && j == 3) || (i == 1 && (j == 1 || j == 3 || j == 5)) || (i == 2) || (i == 3) || (i == 4 && (j == 2 || j == 3 || j == 4)))
						cout << "_";
					else
						cout << " ";
				}
		}
}

void Santa_Raindeer(int x, int y)
{
	for (int i = 0; i<3; i++)
		{
			gotoxy(x, y + i);
			for (int j = 0; j<17; j++)
				{
					if (i == 0 && j == 0)
						cout << "~";
					else if (i == 0 && j == 1)
						cout << "6";
					else if (i == 0 && j == 3)
						cout << ".";
					else if ((i == 0 && (j == 2 || j == 4 || j == 5 || j == 9 || j == 13)) || (i == 1 && (j == 1 || j == 2)))
						cout << "_";
					else if (i == 0 && (j == 6 || j == 10 || j == 14))
						cout << ",";
					else if (i == 0 && (j == 8 || j == 12 || j == 16))
						cout << "P";
					else if ((i == 0 && (j == 7 || j == 11 || j == 15)) || (i == 2 && j == 5))
						cout << "`";
					else if (i == 1 && j == 0)
						 cout << "|";
					else if (i == 1 && j == 3)
						cout << ")";
					else if (i == 1 && (j == 6 || j == 10 || j == 14))
						cout << "/";
					else if (i == 1 && (j == 7 || j == 11 || j == 15))
						cout << "^";
					else if (i == 1 && (j == 8 || j == 12 || j == 16))
						cout << "\\";
					else if (i == 2 && (j == 0 || j == 2 || j == 4))
						cout << "=";
					else if (i == 2 && (j == 1 || j == 3))
						cout << "'";
					else
						cout << " ";
				}
		}
}

void Star(int x, int y)
{
	for (int k = 0; k<5; k++)
		{
			gotoxy(x, y + k);
			for (int l = 0; l<5; l++)
				{
					if ((k == 0 || k == 4) && l == 2)
						cout << "|";
					else if (k == 2 && (l == 0 || l == 4))
						cout << "-";
					else if ((k == 1 && l == 1) || (k == 3 && l == 3))
						cout << "\\";
					else if ((k == 1 && l == 3) || (k == 3 && l == 1))
						cout << "/";
					else if (k == 2 && l == 2)
						cout << "*";
					else
						cout << " ";
				}
		}                             
}

void Clear(int x, int y, int r, int c)
{
	for(int i=0; i<r; i++)
		{
			gotoxy(x, y + i);
			for(int j=0; j<c; j++)
				cout<<" ";
		}
}

void Animation1(int x, int y)
{
	for(int i=0; i<5; i++)
		{
			Clear(x+i-1,y,3,17);
			Sleep(200); 
			Santa_Raindeer(x+i,y);
			Sleep(200);
		}

	Clear(x+4,y,3,17);
	Santa_Raindeer(x,y);
}

void Animation2(int x, int y)
{
	Clear(x,y,5,5);
	Sleep(500);
	Star(x,y);
	Sleep(500);
}

void main()
{
	char Name[20], Greeting[40];
	cout<<"Enter Name :  ";
	cin.getline(Name, 20);
	cout<<"Enter Greeting : ";
	cin.getline(Greeting, 40);
	cout << endl;
	Border();
	gotoxy(2, 10);
	cout << "Dear " << Name << ",";
	gotoxy(4, 12);
	cout << "Merry Christmas !!";
	gotoxy(6, 14);
	cout << Greeting;
	gotoxy(25, 30);
	cout << "From Satan";
	Santa_Raindeer(2,5);
	Star(30, 5);
	Tree(3, 15);
	Gift(2, 27);
	Gift(16, 27);
	Snowman(27, 23);
	Animation1(2,5);
	for( int i=0; i<5; i++)
	Animation2(30,5);
}