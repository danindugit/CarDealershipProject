import java.io.IOException;

public class CarList {

        private Car list[];
        private int maxSize;
        private int size;


        public  CarList() {
                this.maxSize = 20;
                this.list  = new Car[maxSize];
                this.size= 0 ;

        }


        public Car[] getList() {
                return list;
        }


        public void setList(Car[] list) {
                this.list = list;
        }


        public int getMaxSize() {
                return maxSize;
        }


        public void setMaxSize(int maxSize) {
                this.maxSize = maxSize;
        }


        public int getSize() {
                return size;
        }


        public void setSize(int size) {
                this.size = size;
        }

        public boolean insert (Car record) {
                if (size < maxSize) {
                        size++;
                        list [size-1] = record; // success
                        return true;
                }
                else {
                        return false;  // no more space
                }
        }
        
        public void sortByVin() {
        	for (int top = 0; top < this.size; top++)
            {                       
                    Car tempRecord = new Car();
                    tempRecord= list[top];
                    String item = list[top].getVin();
                    int i = top;
                    while (i > 0 && item.compareToIgnoreCase((list[i-1].getVin())) < 0)
                    {
                            list[i] = list[i-1];
                            i--;}
                    list[i] = tempRecord;
            }
        }


        public  void SortByBrand ()
        {

                for (int top = 0; top < this.size; top++)
                {                       
                        Car tempRecord = new Car();
                        tempRecord= list[top];
                        String item = list[top].getBrand();
                        int i = top;
                        while (i > 0 && item.compareToIgnoreCase((list[i-1].getBrand())) < 0)
                        {
                                list[i] = list[i-1];
                                i--;}
                        list[i] = tempRecord;
                }
        }//END INSERTION SORT FOR BRAND


        public  void SortByModel ()
        {

                for (int top = 0; top < this.size; top++)
                {                       
                        Car tempRecord = new Car();
                        tempRecord= list[top];
                        String item = list[top].getModel();
                        int i = top;
                        while (i > 0 && item.compareToIgnoreCase((list[i-1].getModel())) < 0)
                        {
                                list[i] = list[i-1];
                                i--;}
                        list[i] = tempRecord;
                }
        }//END INSERTION SORT FOR BRAND




        public void sortByPrice() 
        { 
                //sorts from lowest to greatest
                int n = this.getSize(); 

                // Start with a big gap, then reduce the gap 
                for (int gap = n/2; gap > 0; gap /= 2) 
                { 
                        // Do a gapped insertion sort for this gap size. 
                        // The first gap elements a[0..gap-1] are already 
                        // in gapped order keep adding one more element 
                        // until the entire array is gap sorted 
                        for (int i = gap; i < n; i++) 
                        { 
                                // add a[i] to the elements that have been gap 
                                // sorted save a[i] in temp and make a hole at 
                                // position i 
                                Car temp;
                                temp = list[i];

                                // shift earlier gap-sorted elements up until 
                                // the correct location for a[i] is found 
                                int j; 
                                for (j = i; j >= gap && list[j - gap].getPrice() < temp.getPrice(); j -= gap) 
                                {
                                        list[j] = list[j - gap]; 
                                }

                                // put temp (the original a[i]) in its correct 
                                // location 
                                //list[j].getYear() = temp;
                                //temp = list[j];
                                list[j]=temp;

                        } 
                } 

        }//END SHELL SORT FOR PRICE 


        public void sortByYear() 
        { 
                //sorts from lowest to greatest
                int n = this.getSize(); 

                // Start with a big gap, then reduce the gap 
                for (int gap = n/2; gap > 0; gap /= 2) 
                { 
                        // Do a gapped insertion sort for this gap size. 
                        // The first gap elements a[0..gap-1] are already 
                        // in gapped order keep adding one more element 
                        // until the entire array is gap sorted 
                        for (int i = gap; i < n; i++) 
                        { 
                                // add a[i] to the elements that have been gap 
                                // sorted save a[i] in temp and make a hole at 
                                // position i 
                                Car temp;
                                temp = list[i];

                                // shift earlier gap-sorted elements up until 
                                // the correct location for a[i] is found 
                                int j; 
                                for (j = i; j >= gap && list[j - gap].getYear() < temp.getYear(); j -= gap) 
                                {
                                        list[j] = list[j - gap]; 
                                }

                                // put temp (the original a[i]) in its correct 
                                // location 
                                //list[j].getYear() = temp;
                                //temp = list[j];
                                list[j]=temp;

                        } 
                } 

        }//END SHELL SORT FOR YEAR(latest to oldest


        //binary search for the list
        public int SearchForBrand(String searchKey) {
                int low = 0;
                int high = this.size -1;
                int middle;

                this.SortByBrand();

                while(low <= high) {
                        middle = (high + low)/2; //find the middle of list

                        //if equals 
                        if (searchKey.compareToIgnoreCase(list[middle].getBrand())==0) {
                                return middle;  //return location found
                        }
                        //if in the lower part of the list
                        else if (searchKey.compareToIgnoreCase(list[middle].getBrand()) < 0) {
                                high = middle - 1;  //change high
                        }

                        //if in the higher part of the list
                        else {
                                low = middle + 1;  // change the low end of the list
                        }
                }


                return -1;//not found
        }


        //binary search for the list
        public int SearchForModel(String searchKey) {
                int low = 0;
                int high = this.size -1;
                int middle;

                this.SortByModel();

                while(low <= high) {
                        middle = (high + low)/2; //find the middle of list

                        //if equals 
                        if (searchKey.compareToIgnoreCase(list[middle].getModel())==0) {
                                return middle;  //return location found
                        }
                        //if in the lower part of the list
                        else if (searchKey.compareToIgnoreCase(list[middle].getModel()) < 0) {
                                high = middle - 1;  //change high
                        }

                        //if in the higher part of the list
                        else {
                                low = middle + 1;  // change the low end of the list
                        }
                }


                return -1;//not found
        }
        
        public int searchForVin(String searchKey) {
        	int low = 0;
            int high = this.size -1;
            int middle;

            this.sortByVin();

            while(low <= high) {
                    middle = (high + low)/2; //find the middle of list

                    //if equals 
                    if (searchKey.compareToIgnoreCase(list[middle].getVin())==0) {
                            return middle;  //return location found
                    }
                    //if in the lower part of the list
                    else if (searchKey.compareToIgnoreCase(list[middle].getVin()) < 0) {
                            high = middle - 1;  //change high
                    }

                    //if in the higher part of the list
                    else {
                            low = middle + 1;  // change the low end of the list
                    }
            }


            return -1;//not found
        }

        public String toString() {
                String theList = "";
                for(int i = 0; i < this.size; i++) {
                        //theList = theList + "Record " + i + ": " + list[i].toString() + "\n";
                        theList = theList + list[i].toString() + "\n";
                } 
                return theList;
        }

        public String DisplayString() {
                String theList = "";
                for(int i = 0; i < this.size; i++) {
                        theList = theList + "Record " + i + ": " + list[i].DisplayString() + "\n";
                        //theList = theList + list[i].toString() + "\n";
                } 
                return theList;
        }

        public String toString(int location) {
                String out = "";

                out = list[location].toString();

                return out;

        }

        public boolean change(Car oldCar, Car newCar) {
                if (delete(oldCar)) {

                        return insert(newCar);
                }
                return false;
        }

        public boolean delete (Car record) {
                // loop through my list 
                String VIN = record.getVin();
                int search = this.searchForVin(VIN);

                if( search >= 0)
                {
                        list[search] = list[size-1];
                        size--;
                        this.SortByBrand();
                        return true;


                }

                //              for (int i = 0; i < size; i++) {
                //                      // if the name is found
                //                      if(list[i].getMake().equalsIgnoreCase(record.getMake())) {
                //                              list[i] = list[size-1];  //place the last element in my record
                //                              // by overwriting my record
                //                              size--;                  // decrease the size
                //                              return true;             // sucessfull delete
                //                      }
                //              }
                this.SortByBrand();
                return false;   // could not delete
        }
        public static void main(String[] args) {
                CarList carRecord = new CarList();

                //CAR ISSUES             
                /*Oil changes
                 *  Brake replacement
                 * tuning problems
                 * Air conditioning problems
                 * Engine problems
                 * Perfect
                 */

                //STATUS : Done SERVICe AND SERVICED

                String cRec1 = "Toyota,Rav4,2018,3400,o,s,A1B2C3";
                String cRec2 = "Kia,Sante Fe,2009,3000,t,d,A2B1C3";
                String cRec3 = "Honda,Civic,2002,2000,b,s,A3B2C3";
                String cRec4 = "Kia,Sante Fe,2009,3000,p,w,C3B2A1";
                String cRec5 = "Toyota,Avalon,2020,500,a,d,C1B2A3";
                String cRec6 = "Lamb,Bob,1990,4500,e,w,C2B3A1";

                Car cInfo1 = new Car();
                Car cInfo2 = new Car();
                Car cInfo3 = new Car();
                Car cInfo4 = new Car();
                Car cInfo5 = new Car();
                Car cInfo6 = new Car();

                cInfo1.processRecord(cRec1);
                cInfo2.processRecord(cRec2);
                cInfo3.processRecord(cRec3);
                cInfo4.processRecord(cRec4);
                cInfo5.processRecord(cRec5);
                cInfo6.processRecord(cRec6);


                if(!carRecord.insert(cInfo1)) {
                        System.out.println( "Record not added");
                }
                else {
                        System.out.println( "record information insertion was Successful");
                }

                System.out.println(carRecord.toString());


                System.out.println("changing records");
                carRecord.change(cInfo1, cInfo2);
                System.out.println(carRecord.toString());
                System.out.println("deleting records");
                carRecord.delete(cInfo2);
                System.out.println(carRecord.toString());


                if(!carRecord.insert(cInfo1)) {
                        System.out.println( "Record 1 not added");
                }
                else {
                        System.out.println( "record 1 information insertion was Successful");
                }   

                if(!carRecord.insert(cInfo2)) {
                        System.out.println( "Record 2 not added");
                }
                else {
                        System.out.println( "record 2 information insertion was Successful");
                }    
                if(!carRecord.insert(cInfo3)) {
                        System.out.println( "Record 3 not added");
                }
                else {
                        System.out.println( "record 3 information insertion was Successful");
                }    
                if(!carRecord.insert(cInfo4)) {
                        System.out.println( "Record 4 not added");
                }
                else {
                        System.out.println( "record 4 information insertion was Successful");
                }    
                if(!carRecord.insert(cInfo5)) {
                        System.out.println( "Record 5 not added");
                }
                else {
                        System.out.println( "record 5 information insertion was Successful");
                }    

                if(!carRecord.insert(cInfo6)) {
                        System.out.println( "Record 6 not added");
                }
                else {
                        System.out.println( "record 6 information insertion was Successful");
                }

                System.out.println(carRecord.toString());

                carRecord.SortByBrand();
                System.out.println( "record sorted by brand");
                System.out.println(carRecord.toString());

                carRecord.sortByPrice();

                System.out.println( "\nrecord sorted by price");
                System.out.println(carRecord.toString());

                carRecord.sortByYear();
                System.out.println(carRecord.toString());


                System.out.println(carRecord.toString(2));

        }



}//end CarList
