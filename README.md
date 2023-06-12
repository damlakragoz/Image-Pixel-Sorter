# ImagePixelSorter
This Java program sorts the pixels of an image based on brightness value by implementing a 2D Bubble Sort algorithm. The sorting technique used in this program is an adaptation of Bubble Sort to 2D matrices. The sort is called by a timer repeatedly to visualize the pixel sorting process.

I coded this Java program for my CS102 class as a lab assignment on my second semester in Bilkent University.

## Detailes

#### The Original Picture

<img width="221" alt="ss" src="https://github.com/ph7oeuf/Image-Pixel-Sorter/assets/77412814/2dc38214-7e10-4aa9-a591-2bebde4a1c4c">


startAnimatedBubbleSort method periodically calls the diagonalStep method which calls verticalStep and horizontalStep methods.
Below is the result of the Animated Bubble Sort. 

![diagonal](https://github.com/ph7oeuf/Image-Pixel-Sorter/assets/77412814/cbe9ba68-b4a7-450b-b255-5a18f8d737f1)


horizontalStep method performs one Bubble Sort step on the pixels of all rows in the pixel matrix.
Below is the result when verticalStep is called periodically, and the picture's rows are sorted individually.

<img width="511" alt="vertical" src="https://github.com/ph7oeuf/Image-Pixel-Sorter/assets/77412814/e6555968-c389-48bc-8436-24153e50806b">


horizontalStep method performs one Bubble Sort step on the pixels of all columns in the pixel matrix.
Below is the result when horizontalStep is called periodically, and the picture's rows are sorted individually.

<img width="509" alt="horiz" src="https://github.com/ph7oeuf/Image-Pixel-Sorter/assets/77412814/1f772e7b-8911-4d8b-9699-81e3a377d60d">

## Additional Feature
### Pressing the key...
   'd' changes the diagonalStep methods period dividing it by 2. The sorting animation is now x2 times faster.
            
   'a' changes the diagonalStep methods period multiplying it by 2. The sorting animation is now x2 times slower.
          
   'r' pauses and resumes the sort animation.
