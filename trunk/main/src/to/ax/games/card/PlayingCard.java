package to.ax.games.card;


/**
 * See http://en.wikipedia.org/wiki/Playing_card#Anglo-American-French
 * 
 * Note that the ordering of the cards is arbitrary and isn't necessarily of 
 * use for any specific game or Rules, but roughly corresponds to the order
 * of cards in the game of bridge.
 * 
 * @author Tom Ritchford (tom@swirly.com)
 *
 */
public enum PlayingCard {
  ACE_OF_SPADES,    ACE_OF_HEARTS,    ACE_OF_CLUBS,    ACE_OF_DIAMONDS,   
  KING_OF_SPADES,   KING_OF_HEARTS,   KING_OF_CLUBS,   KING_OF_DIAMONDS,
  QUEEN_OF_SPADES,  QUEEN_OF_HEARTS,  QUEEN_OF_CLUBS,  QUEEN_OF_DIAMONDS,
  JACK_OF_SPADES,   JACK_OF_HEARTS,   JACK_OF_CLUBS,   JACK_OF_DIAMONDS,
  TEN_OF_SPADES,    TEN_OF_HEARTS,    TEN_OF_CLUBS,    TEN_OF_DIAMONDS,
  NINE_OF_SPADES,   NINE_OF_HEARTS,   NINE_OF_CLUBS,   NINE_OF_DIAMONDS,  
  EIGHT_OF_SPADES,  EIGHT_OF_HEARTS,  EIGHT_OF_CLUBS,  EIGHT_OF_DIAMONDS,
  SEVEN_OF_SPADES,  SEVEN_OF_HEARTS,  SEVEN_OF_CLUBS,  SEVEN_OF_DIAMONDS,
  SIX_OF_SPADES,    SIX_OF_HEARTS,    SIX_OF_CLUBS,    SIX_OF_DIAMONDS, 
  FIVE_OF_SPADES,   FIVE_OF_HEARTS,   FIVE_OF_CLUBS,   FIVE_OF_DIAMONDS,
  FOUR_OF_SPADES,   FOUR_OF_HEARTS,   FOUR_OF_CLUBS,   FOUR_OF_DIAMONDS,
  THREE_OF_SPADES,  THREE_OF_HEARTS,  THREE_OF_CLUBS,  THREE_OF_DIAMONDS,
  TWO_OF_SPADES,    TWO_OF_HEARTS,    TWO_OF_CLUBS,    TWO_OF_DIAMONDS, 
;
  public static final int DECK_SIZE = PlayingCard.values().length;
  
  private PlayingCard() {
    // This convenient trick relies on never changing any of the order of the enums above.
    this.rank = Rank.values()[ordinal() / 4];
    this.suit = Suit.values()[ordinal() % 4];
  }
  
  private final Rank rank;
  private final Suit suit;

  public Suit suit() { return suit; }
  public Rank rank() { return rank; }
  
  // Useful synonyms for the lazy.
  public static final PlayingCard _AS = ACE_OF_SPADES;         
  public static final PlayingCard _AH = ACE_OF_HEARTS;         
  public static final PlayingCard _AC = ACE_OF_CLUBS;          
  public static final PlayingCard _AD = ACE_OF_DIAMONDS;       
  public static final PlayingCard _KS = KING_OF_SPADES;        
  public static final PlayingCard _KH = KING_OF_HEARTS;        
  public static final PlayingCard _KC = KING_OF_CLUBS;         
  public static final PlayingCard _KD = KING_OF_DIAMONDS;      
  public static final PlayingCard _QS = QUEEN_OF_SPADES;       
  public static final PlayingCard _QH = QUEEN_OF_HEARTS;       
  public static final PlayingCard _QC = QUEEN_OF_CLUBS;        
  public static final PlayingCard _QD = QUEEN_OF_DIAMONDS;     
  public static final PlayingCard _JS = JACK_OF_SPADES;        
  public static final PlayingCard _JH = JACK_OF_HEARTS;        
  public static final PlayingCard _JC = JACK_OF_CLUBS;         
  public static final PlayingCard _JD = JACK_OF_DIAMONDS;      
  public static final PlayingCard _TS = TEN_OF_SPADES;         
  public static final PlayingCard _TH = TEN_OF_HEARTS;         
  public static final PlayingCard _TC = TEN_OF_CLUBS;          
  public static final PlayingCard _TD = TEN_OF_DIAMONDS;       
  public static final PlayingCard _9S = NINE_OF_SPADES;        
  public static final PlayingCard _9H = NINE_OF_HEARTS;        
  public static final PlayingCard _9C = NINE_OF_CLUBS;         
  public static final PlayingCard _9D = NINE_OF_DIAMONDS;      
  public static final PlayingCard _8S = EIGHT_OF_SPADES;       
  public static final PlayingCard _8H = EIGHT_OF_HEARTS;       
  public static final PlayingCard _8C = EIGHT_OF_CLUBS;        
  public static final PlayingCard _8D = EIGHT_OF_DIAMONDS;     
  public static final PlayingCard _7S = SEVEN_OF_SPADES;       
  public static final PlayingCard _7H = SEVEN_OF_HEARTS;       
  public static final PlayingCard _7C = SEVEN_OF_CLUBS;        
  public static final PlayingCard _7D = SEVEN_OF_DIAMONDS;     
  public static final PlayingCard _6S = SIX_OF_SPADES;         
  public static final PlayingCard _6H = SIX_OF_HEARTS;         
  public static final PlayingCard _6C = SIX_OF_CLUBS;          
  public static final PlayingCard _6D = SIX_OF_DIAMONDS;       
  public static final PlayingCard _5S = FIVE_OF_SPADES;        
  public static final PlayingCard _5H = FIVE_OF_HEARTS;        
  public static final PlayingCard _5C = FIVE_OF_CLUBS;         
  public static final PlayingCard _5D = FIVE_OF_DIAMONDS;      
  public static final PlayingCard _4S = FOUR_OF_SPADES;        
  public static final PlayingCard _4H = FOUR_OF_HEARTS;        
  public static final PlayingCard _4C = FOUR_OF_CLUBS;         
  public static final PlayingCard _4D = FOUR_OF_DIAMONDS;      
  public static final PlayingCard _3S = THREE_OF_SPADES;       
  public static final PlayingCard _3H = THREE_OF_HEARTS;       
  public static final PlayingCard _3C = THREE_OF_CLUBS;        
  public static final PlayingCard _3D = THREE_OF_DIAMONDS;     
  public static final PlayingCard _2S = TWO_OF_SPADES;         
  public static final PlayingCard _2H = TWO_OF_HEARTS;         
  public static final PlayingCard _2C = TWO_OF_CLUBS;          
  public static final PlayingCard _2D = TWO_OF_DIAMONDS;       
}
