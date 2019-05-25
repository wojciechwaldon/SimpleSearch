
At start i 

I consider two options:

1. Create Map<Document, Phrase>
   Phrase containing value and it's TF*IDF
   
It will be more demanding at saving document, but faster during searching exact phrase;

Steps:
 * Calculating TF*IDF for every phrase in database (caused putting new elements)
 * Searching for every element containing exact phrase
 * Sor by calculated TF*IDF value

2. Create map <Phrase, List<Document>>

Steps
 * Putting 
