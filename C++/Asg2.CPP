#include<iostream> 
#include<fstream>   
using namespace std;

char ASCII(double r, double g, double b)
{
 double gray = (0.3*r/256 + 0.6*g/256 + 0.11*b/256);

 if (gray > 0.6)
  return (char) 65 + (gray - 0.6)*(1 / (0.41 / 26)); 
 else if (gray >= 0.3 && gray <= 0.6)
  return (char) 97 + (gray - 0.3)*(1 / (0.3/26));
 else if (gray < 0.3)
  return (char) 32 + (gray * (1 / (0.3 / 16)));
}

void main()
{
int image=0;
 int red, blue, green;
 char Asc;
 char temp[500][500];
char filename[50];
 int rgb;
 unsigned short signature;
 unsigned int file_size;
 unsigned int reserve;
 unsigned int offset;
 unsigned int header_size;
 int width;
 int height;
 unsigned short planes;
 unsigned short bits_per_pixel;
 unsigned int compression;
 unsigned int data_size;
 unsigned int horizontal_res;
 unsigned int vertical_res;
 unsigned int num_color;
 unsigned int imp_color;

 ifstream fin;

cin >> filename;
 fin.open(filename, ios::binary);

 while (fin.is_open() == false)
 {
  cin >> filename;
  fin.open(filename, ios::binary);
 }

fin.read((char *)&signature, 2); // read signature
 fin.read((char *)&file_size, 4); // read fileSize; 
 fin.read((char *)&reserve, 4); // read reserve; 
 fin.read((char *)&offset, 4); // read offset;
 fin.read((char *)&header_size, 4);

 if (header_size == 40)
 {
  fin.read((char *)&width, 4);
  fin.read((char *)&height, 4);
  fin.read((char *)&planes, 2);
  fin.read((char *)&bits_per_pixel, 2);
  fin.read((char *)&compression, 4);
  fin.read((char *)&data_size, 4);
  fin.read((char *)&horizontal_res, 4);
  fin.read((char *)&vertical_res, 4);
  fin.read((char *)&num_color, 4);
  fin.read((char *)&imp_color, 4);
 }

 else if (header_size == 12)
 {
  fin.read((char *)&width, 2);
  fin.read((char *)&height, 2);
  fin.read((char *)&planes, 2);
  fin.read((char *)&bits_per_pixel, 2);
  fin.read((char *)&compression, 4);
  fin.read((char *)&data_size, 4);
  fin.read((char *)&horizontal_res, 4);
  fin.read((char *)&vertical_res, 4);
  fin.read((char *)&num_color, 4);
  fin.read((char *)&imp_color, 4);
 }


 for (int i = 0; i < height; i++)
 {
  for (int j = 0; j < width; j++)
  {
   fin.read((char *)&image, 3);
   red = (image >> 16) & 0xFF;
   green = (image >> 8) & 0xFF;
   blue = (image >> 0) & 0xFF;

   Asc = ASCII(red, green, blue);
   temp[i][j] = Asc;
  }
 }

 for (int k = height - 1; k >= 0; k--)
 {
  for (int l = 0; l < width; l++)
  {
   cout << temp[k][l];
  }
  cout << endl;
 }
}
