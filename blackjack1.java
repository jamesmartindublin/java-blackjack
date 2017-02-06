/**
  *
  * Console Blackjack Game
  *
  * version 1.0 from 31/01/2017
  * James Martin 
  */
import java.util.Scanner;
import java.util.Random;

public class blackjack1 {
  
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    
    String player_name;
    int cardScore = 0;
    int dealerScore = 0;
    int playerScore = 0;
    int balance = 0;
    String hitOrStand = "h";
    String play = "y";       //play again flag
    int blackJack = 0;       //black jack flag
    int bust = 0;            //bust flag
    int gameOver = 0;        //game over flag
        
    
    System.out.println("         *********************");
    System.out.println("         Welcome to the Casino");
    System.out.println("         *********************\n\n");
    
    System.out.println("         Please Enter Your Name\n\n");
    player_name = input.next();
    System.out.println("         How Many Chips would you like to buy?");
    balance = input.nextInt();
    
    
    System.out.println("         Welcome, "+ player_name);
    
    while ((play.equals("y")) && (balance > 0 )) { 
      
      //reset the flags
      gameOver = 0;
      cardScore = 0;
      blackJack = 0;
      bust = 0;
      hitOrStand = "h";
    
      System.out.println("\n         Your Balance is currently "+ balance);
    
      String[] deckOfCards = cardArray();   //an array of cards is placed into the String var
       
      String firstCard = oneCardGen(deckOfCards);           //generates one card and places value into variable
      String secondCard = oneCardGen(deckOfCards);
    
      System.out.println("\n         Place your bet!");
      int bet = input.nextInt();
    
      
    
      cardScore = deal2Cards(firstCard, secondCard, cardScore);   //teh first deals puts the card score value into the cardScore variable
    
      System.out.println("\n         Your first card is the " + firstCard);
      System.out.println("\n         Your second card is the " + secondCard);
      System.out.println("\n         You have " + cardScore);
    
      while ((hitOrStand.equals("h")) && (bust == 0) && (blackJack == 0))  { 
      
    
        if ((cardScore == 21) && (blackJack == 0)) {
          System.out.println("\n         Blackjack!!!");
          blackJack = 1;   //a blackjack flag - if 1 then skips the dealer play portion
          balance += bet;
          //cardScore = 0;
          System.out.println("\n         Your balance is now " + balance);
          System.out.println("\n         Would you like to play again?");
          play = input.next();
                 
        }
        if ((cardScore > 21) && (bust == 0)){
          System.out.println("\n         You're Bust!!!");
          balance -= bet;
          
          //cardScore = 0;  
          bust = 1;     // set so that it skips the if's and loops back
          
          if (balance > 0 ) {
            System.out.println("\n         Your balance is now " + balance);
            System.out.println("\n         Would you like to play again?");
            play = input.next();
            } // end of if
          else {
            System.out.println("\n         You're broke");
            System.out.println("\n         Get out of the Casino");
            break;
          } // end of if-else
        }
        if ((cardScore < 21) && (bust == 0)){
               
          System.out.println("\n         Hit or Stand?");
          hitOrStand = input.next();
          
          if (hitOrStand.equals("h")) {
            firstCard = oneCardGen(deckOfCards);
            cardScore = dealOneCard(firstCard, cardScore);
            System.out.println("\n         You drew the " + firstCard);
            System.out.println("\n         You have " + cardScore);
            
            if ((cardScore == 21)) {
               System.out.println("\n         You Win!");
               System.out.println("\n         Would you like to play again?");
               play = input.next();
               blackJack = 1;               
              
            } // end of if                     
          } // end of if
          
          
          //  the dealers game starts once hit or stand = s
          if (hitOrStand.equals("s")) {
            playerScore = cardScore;
            cardScore = 0;
            System.out.println("\n         The Player stands....Dealer to play");
              
            firstCard = oneCardGen(deckOfCards);
            secondCard = oneCardGen(deckOfCards);
    
            dealerScore = deal2Cards(firstCard, secondCard, cardScore);
            cardScore = dealerScore;
    
            System.out.println("\n         Dealer's first card is the " + firstCard);
            System.out.println("\n         Dealer's second card is the " + secondCard);
            System.out.println("\n         Dealer has " + dealerScore);
            
            
            if (dealerScore == 21) {
              System.out.println("         Dealer has Black Jack!!");
              balance -= bet;
              blackJack = 1;
              System.out.println("\n         Your balance is now " + balance);
              System.out.println("\n         Would you like to play again?");
              play = input.next();
            } // end of if
            
            while ((dealerScore <22) && (blackJack == 0) && (gameOver == 0)) { 
              
              if (dealerScore < 17) {
                firstCard = oneCardGen(deckOfCards);
                dealerScore = dealOneCard(firstCard, cardScore);
                System.out.println("\n         Dealer draws the " + firstCard);
                System.out.println("\n         Dealer has " + dealerScore);  
              } // end of if
              
              
              if (dealerScore > 21) {
                System.out.println("\n         Dealer is bust");
                System.out.println("\n         You win!!");
                gameOver = 1;
                balance += bet;
                System.out.println("\n         Your balance is now " + balance);
                System.out.println("\n         would you like to play again?");
                play = input.next();
              } // end of if
              
              if ((dealerScore > 17) && (dealerScore < 22)) {
            
                System.out.println("\n         Dealer stands");
                
                //Comparison to find out who won
                
                if (playerScore == dealerScore) {
                  System.out.println("\n         It's a draw");
                  gameOver = 1;
                  System.out.println("\n         Would you like to play again?");
                  play = input.next();
                 }
                 
                if (playerScore > dealerScore) {
                  System.out.println("\n         You win!!");
                  gameOver = 1;
                  balance += bet;
                  System.out.println("\n         Your balance is now " + balance);
                  System.out.println("\n         Would you like to play again?");
                  play = input.next();
                 } // end of if
                 
                if (playerScore < dealerScore) {
                  System.out.println("\n         You lose!!");
                  gameOver = 1;
                  balance -= bet;
                  System.out.println("\n         Your balance is now " + balance);
                  System.out.println("\n         Would you like to play again?");
                  play = input.next();
                 } // end of if
                             
              } // end of if

          } // end of if

        } // end of if

      } // end of if
    }

  } // end of while
} // end of main


  
  //Methods
  //--------------------------------------------------

    
    public static String[] cardArray(){             // a method that creates an array of strings and populates 
                                                    // it with all the card names and returns the array
    String[] deckOfCards;
    deckOfCards = new String[52];
    
    deckOfCards[0] = "Ace of Spades";
    deckOfCards[1] = "2 of Spades";
    deckOfCards[2] = "3 of Spades";
    deckOfCards[3] = "4 of Spades";
    deckOfCards[4] = "5 of Spades";
    deckOfCards[5] = "6 of Spades";
    deckOfCards[6] = "7 of Spades";
    deckOfCards[7] = "8 of Spades";
    deckOfCards[8] = "9 of Spades";
    deckOfCards[9] = "10 of Spades";
    deckOfCards[10] = "Jack of Spades";
    deckOfCards[11] = "Queen of Spades";
    deckOfCards[12] = "King of Spades";
    
    deckOfCards[13] = "Ace of Clubs";
    deckOfCards[14] = "2 of Clubs";
    deckOfCards[15] = "3 of Clubs";
    deckOfCards[16] = "4 of Clubs";
    deckOfCards[17] = "5 of Clubs";
    deckOfCards[18] = "6 of Clubs";
    deckOfCards[19] = "7 of Clubs";
    deckOfCards[20] = "8 of Clubs";
    deckOfCards[21] = "9 of Clubs";
    deckOfCards[22] = "10 of Clubs";                
    deckOfCards[23] = "Jack of Clubs";
    deckOfCards[24] = "Queen of Clubs";
    deckOfCards[25] = "King of Clubs";
    
    deckOfCards[26] = "Ace of Diamonds";
    deckOfCards[27] = "2 of Diamonds";
    deckOfCards[28] = "3 of Diamonds";
    deckOfCards[29] = "4 of Diamonds";
    deckOfCards[30] = "5 of Diamonds";
    deckOfCards[31] = "6 of Diamonds";
    deckOfCards[32] = "7 of Diamonds";
    deckOfCards[33] = "8 of Diamonds";
    deckOfCards[34] = "9 of Diamonds";
    deckOfCards[35] = "10 of Diamonds"; 
    deckOfCards[36] = "Jack of Diamonds";
    deckOfCards[37] = "Queen of Diamonds";
    deckOfCards[38] = "King of Diamonds";
    
    deckOfCards[39] = "Ace of Hearts";
    deckOfCards[40] = "2 of Hearts";
    deckOfCards[41] = "3 of Hearts";
    deckOfCards[42] = "4 of Hearts";
    deckOfCards[43] = "5 of Hearts";
    deckOfCards[44] = "6 of Hearts";
    deckOfCards[45] = "7 of Hearts";
    deckOfCards[46] = "8 of Hearts";
    deckOfCards[47] = "9 of Hearts";
    deckOfCards[48] = "10 of Hearts";
    deckOfCards[49] = "Jack of Hearts";
    deckOfCards[50] = "Queen of Hearts";
    deckOfCards[51] = "King of Hearts";
    
    return deckOfCards;      
  }
  
  public static String oneCardGen(String[] deckOfCards){
    Random rand = new Random();                          // method that pulls in the sting array with athe card names,
    int randomNum = rand.nextInt(52);                    // creates a random number between 0 and 51 and returns the card name
    String oneCard = deckOfCards[randomNum];             // from the array
    return oneCard; 
    }
    
  public static int deal2Cards(String firstCard, String secondCard, int cardScore){
                                                          // method takes in the 2 cards and the current card score and looks
    switch(firstCard){                                    // up the card name and adds its score to the card score
      
      case "10 of Spades": case "Jack of Spades": case "Queen of Spades": case "King of Spades":
      case "10 of Clubs": case "Jack of Clubs": case "Queen of Clubs": case "King of Clubs":
      case "10 of Diamonds": case "Jack of Diamonds": case "Queen of Diamonds": case "King of Diamonds":
      case "10 of Hearts": case "Jack of Hearts": case "Queen of Hearts": case "King of Hearts":
    
           cardScore += 10;
           break;
      
      case "Ace of Hearts": case "Ace of Diamonds": case "Ace of Clubs": case "Ace of Spades":
      
           cardScore += 11;
           break;
           
      case "2 of Spades": case "2 of Clubs": case "2 of Diamonds": case "2 of Hearts":
      
           cardScore += 2;
           break;
           
      case "3 of Spades": case "3 of Clubs": case "3 of Diamonds": case "3 of Hearts":
           cardScore += 3;
           break;
      
      case "4 of Spades": case "4 of Clubs": case "4 of Diamonds": case "4 of Hearts":
           cardScore += 4;
           break;
           
      case "5 of Spades": case "5 of Clubs": case "5 of Diamonds": case "5 of Hearts":
           cardScore += 5;
           break;
           
      case "6 of Spades": case "6 of Clubs": case "6 of Diamonds": case "6 of Hearts":
           cardScore += 6;
           break;
           
      case "7 of Spades": case "7 of Clubs": case "7 of Diamonds": case "7 of Hearts":
           cardScore += 7;
           break;
           
      case "8 of Spades": case "8 of Clubs": case "8 of Diamonds": case "8 of Hearts":
           cardScore += 8;
           break;
           
      case "9 of Spades": case "9 of Clubs": case "9 of Diamonds": case "9 of Hearts":
           cardScore += 9;
           break;
    
    
     
    }
    
    switch(secondCard){
      
      case "10 of Spades": case "Jack of Spades": case "Queen of Spades": case "King of Spades":
      case "10 of Clubs": case "Jack of Clubs": case "Queen of Clubs": case "King of Clubs":
      case "10 of Diamonds": case "Jack of Diamonds": case "Queen of Diamonds": case "King of Diamonds":
      case "10 of Hearts": case "Jack of Hearts": case "Queen of Hearts": case "King of Hearts":
    
           cardScore += 10;
           break;
      
      case "Ace of Hearts": case "Ace of Diamonds": case "Ace of Clubs": case "Ace of Spades":
      
           cardScore += 11;
           break;
           
      case "2 of Spades": case "2 of Clubs": case "2 of Diamonds": case "2 of Hearts":
      
           cardScore += 2;
           break;
           
      case "3 of Spades": case "3 of Clubs": case "3 of Diamonds": case "3 of Hearts":
           cardScore += 3;
           break;
      
      case "4 of Spades": case "4 of Clubs": case "4 of Diamonds": case "4 of Hearts":
           cardScore += 4;
           break;
           
      case "5 of Spades": case "5 of Clubs": case "5 of Diamonds": case "5 of Hearts":
           cardScore += 5;
           break;
           
      case "6 of Spades": case "6 of Clubs": case "6 of Diamonds": case "6 of Hearts":
           cardScore += 6;
           break;
           
      case "7 of Spades": case "7 of Clubs": case "7 of Diamonds": case "7 of Hearts":
           cardScore += 7;
           break;
           
      case "8 of Spades": case "8 of Clubs": case "8 of Diamonds": case "8 of Hearts":
           cardScore += 8;
           break;
           
      case "9 of Spades": case "9 of Clubs": case "9 of Diamonds": case "9 of Hearts":
           cardScore += 9;
           break;
         
    }
    
    return cardScore;          
    }
    
  public static int dealOneCard(String firstCard, int cardScore){          // similar to the above method but only takes in one card
    
    switch(firstCard){
      
      case "10 of Spades": case "Jack of Spades": case "Queen of Spades": case "King of Spades":
      case "10 of Clubs": case "Jack of Clubs": case "Queen of Clubs": case "King of Clubs":
      case "10 of Diamonds": case "Jack of Diamonds": case "Queen of Diamonds": case "King of Diamonds":
      case "10 of Hearts": case "Jack of Hearts": case "Queen of Hearts": case "King of Hearts":
    
           cardScore += 10;
           break;
      
      case "Ace of Hearts": case "Ace of Diamonds": case "Ace of Clubs": case "Ace of Spades":
      
           cardScore += 11;
           break;
           
      case "2 of Spades": case "2 of Clubs": case "2 of Diamonds": case "2 of Hearts":
      
           cardScore += 2;
           break;
           
      case "3 of Spades": case "3 of Clubs": case "3 of Diamonds": case "3 of Hearts":
           cardScore += 3;
           break;
      
      case "4 of Spades": case "4 of Clubs": case "4 of Diamonds": case "4 of Hearts":
           cardScore += 4;
           break;
           
      case "5 of Spades": case "5 of Clubs": case "5 of Diamonds": case "5 of Hearts":
           cardScore += 5;
           break;
           
      case "6 of Spades": case "6 of Clubs": case "6 of Diamonds": case "6 of Hearts":
           cardScore += 6;
           break;
           
      case "7 of Spades": case "7 of Clubs": case "7 of Diamonds": case "7 of Hearts":
           cardScore += 7;
           break;
           
      case "8 of Spades": case "8 of Clubs": case "8 of Diamonds": case "8 of Hearts":
           cardScore += 8;
           break;
           
      case "9 of Spades": case "9 of Clubs": case "9 of Diamonds": case "9 of Hearts":
           cardScore += 9;
           break;
    
    
      }
      return cardScore;
      
  }
  
    
             

} // end of class blackjack1
