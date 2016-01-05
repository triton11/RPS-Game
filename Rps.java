//Rock Paper Scissor Wins

public class Rps {
    
    static int playWins = 0;
    static int compWins = 0;
    static int playGames = 0;
    static int compGames = 0;
    static int go = 0;
    static String rw;
    static String rl;
    static String pw;
    static String pl;
    static String sw;
    static String sl;
    
    static int counter = 0;
    static int strat = 0;
    
    static Node first;
    static Node second;
    static Node third;
    static Node start;
    static Node ready;
    public static class Node {
        
        public String item;
        public int num;
        public Node next;
        public Node(String item, int num, Node next) {
            this.num = num;
            this.item = item;
            this.next = next;
        }
        
    }
    
    
    public static void updateScreen() {
        PennDraw.clear(250, 250, 250);
        PennDraw.text(0.8, 0.9, "Player", 0);
        PennDraw.text(0.1, 0.9, "Computer", 0);
        PennDraw.line(0.5, 0.9, 0.5, 0);
        
        PennDraw.text(0.5, 0.95, "Choose r,p or s", 0);
        while (PennDraw.hasNextKeyTyped() == false) {
            continue;
        }
        
        String choice = playChoose();
        String comp = "Rock";
        /*go += 1;
        if (go > 3) {
            strat = analysis();
        }
        */
        if (start == null) {
            start = new Node(choice, 0, null);
        }
        else if (counter <= 20) {
            ready = new Node(choice, counter, null);
            Node track = start;
            while (track.next != null) {
                track = track.next;
            }
            track.next = ready;
            ready.next = null;
        }
        else {
            ready = new Node(choice, counter, null);
            Node track = start;
            while (track.next != null) {
                track = track.next;
            }
            track.next = ready;
            ready.next = null;
            start = start.next;
        }
        /*Node track = start;
        while (track.next != null) {
            System.out.print(track.num + " ");
            track = track.next;
        }
        System.out.println("");
        */        
                
        counter += 1;
        
        if (strat == 1) {
            comp = rw;
        }
        if (strat == 2) {
            comp = sw;
        }
        if (strat == 3) {
            comp = pw;
        }
        if (strat == 4) {
            comp = rl;
        }
        if (strat == 5) {
            comp = sl;
        }
        if (strat == 6) {
            comp = pl;
        }
        else {
            if (counter > 20) {
                comp = randomness();
            }
            else {
                comp = chooseRand();
            }
        }
        //System.out.println(choice);
            
        PennDraw.text(0.8, 0.1, choice, 0);
        PennDraw.text(0.11, 0.1, comp, 0);
        
        
        int x = win(choice, comp);
        
        if (x == 1) {
            PennDraw.setPenColor(PennDraw.RED);
            PennDraw.text(0.8, 0.3, "Player wins!", 0);
            playWins += 1;    
            PennDraw.setPenColor(PennDraw.BLACK);
        }  
        if (x == 2) {
            PennDraw.setPenColor(PennDraw.RED);
            PennDraw.text(0.11, 0.3, "Computer Wins!", 0);
            compWins += 1;  
            PennDraw.setPenColor(PennDraw.BLACK);
        }
 
        if (playWins == 10) {
            playGames += 1;
            playWins = 0;
            compWins = 0;
        }
        if (compWins == 10) {
            compGames += 1;
            playWins = 0;
            compWins = 0;
        }
        
        if (first == null) {
            first = new Node(choice, x, null);
            
        }
        else if (second == null) {
            
            second = new Node(choice, x, null);
            first.next = second;
        }
        else if (third == null) {
            third = new Node(choice, x, null);
            second.next = third;
    
        }
        else {
            first = first.next;
            second = second.next;
            third = new Node(choice, x, null);
            second.next = third;
            
           
            
        }
        /*
        System.out.println("rw = "+ rw);
        System.out.println("sw = "+ sw);
        System.out.println("pw = "+ pw);
        System.out.println("rl = "+ rl);
        System.out.println("sl = "+ sl);
        System.out.println("pl = "+ pl);
        */
        
        
        PennDraw.text(0.8, 0.7, "Player Score", 0);
        PennDraw.text(0.1, 0.7, "Computer Score", 0);
        PennDraw.text(0.95, 0.7, "" + playWins, 0);
        PennDraw.text(0.3, 0.7, "" + compWins, 0);
        PennDraw.text(0.8, 0.5, "Player Games", 0);
        PennDraw.text(0.11, 0.5, "Computer Games", 0);
        PennDraw.text(0.95, 0.5, "" + playGames, 0);
        PennDraw.text(0.3, 0.5, "" + compGames, 0);
        
        while (PennDraw.hasNextKeyTyped() == false) {
            continue;
        }
        
        
        updateScreen();
           
            
               
            
    }
    
        
    public static int win(String x, String y) {
        if (x.equals(y)) {
            return 3;
        }
        else if (x == "Rock" && y == "Scissors" ||
                 x == "Scissors" && y == "Paper" ||
                 x == "Paper" && y == "Rock")
            return 1;
        else {
            return 2;
        }
    }
    
    public static String randomness() {
        Node track = start;
        double r = 0;
        double p = 0;
        double s = 0;
        while(track.next != null) {
            if (track.item.equals("Scissors")) {
                s += 1;
            }
            if (track.item.equals("Rock")) {
                r += 1;
            }
            if (track.item.equals("Paper")) {
                p += 1;
            }
            track = track.next;
        }
        double x = Math.random();
        
        if (x < r/20) {
            return Opposite("Rock");
        }
        else if (x < (r + p)/20) {
            return Opposite("Paper");
        }
        else {
            return Opposite("Scissors");
        }
    }
        
            
    
    public static int analysis() {
        
        if (second.item.equals(third.item) && second.num == 1 && third.num == 1) {
            if (third.item.equals("Rock")) {
                if (rw != null) {
                    return 1; //rw
                }  
            }
            if (third.item.equals("Scissors")) {
                if (sw != null) {
                    return 2; //sw
                }
            }
            if (third.item.equals("Paper")) {
                if (pw != null) {
                    return 3; //rw
                } 
            }
        }
        
        if (second.item.equals(third.item) && second.num == 2 && third.num == 2) {
            if (third.item.equals("Rock")) {
                if (rl != null) {
                    return 4; //rw
                }  
            }
            if (third.item.equals("Scissors")) {
                if (sl != null) {
                    return 5; //sw
                }
            }
            if (third.item.equals("Paper")) {
                if (pl != null) {
                    return 6; //rw
                } 
            }
        }
            
        
        if (first.num == 1 && second.num == 1 && third.num == 1) {
            String f = first.item;
            String s = second.item;
            String t = third.item;
            if (f.equals(s)) {
                if (s.equals("Scissors")) {
                    sw = Opposite(third.item);
                }
                if (s.equals("Rock")) {
                    rw = Opposite(third.item);
                }
                if (s.equals("Paper")) {
                    pw = Opposite(third.item);
                }
            }
            return 0;                       
        }
        
        
        if (first.num == 2 && second.num == 2 && third.num == 1) {
            String f = first.item;
            String s = second.item;
            String t = third.item;
            if (f.equals(s)) {
                if (s.equals("Scissors")) {
                    sl = Opposite(third.item);
                }
                if (s.equals("Rock")) {
                    rl = Opposite(third.item);
                }
                if (s.equals("Paper")) {
                    pl = Opposite(third.item);
                }
            }
            return 0;                       
        }
        
        return 0;
     
    }
    
    public static String Opposite(String l) {
        if (l.equals("Paper")) {
            return "Scissors";
        }
        if (l.equals("Rock")) {
            return "Paper";
        }
        else {
            return "Rock";
        }
    }
    
    public static String chooseRand() {
        
        double x = Math.random();
        
        if (x < 0.33) {
            return "Rock";
        }
        else if (x < 0.66) {
            return "Paper";
        }
        else {
            return "Scissors";
        }
    }
    public static String playChoose() {
        
        if (PennDraw.hasNextKeyTyped()) {
                
                char key = PennDraw.nextKeyTyped();
                
                if (key == 'R' || key == 'r') {
                    return "Rock";
                }
                else if (key == 'P' || key == 'p') {
                    return "Paper";
                }
                else if (key == 'S' || key == 's') {
                    return "Scissors";
                }
                else {
                    return "Invalid";
                }
        }
        else {
            return "Letter";
        }
    }
        
                
        
        
    
    public static void main(String[] args) {
        updateScreen();
        
    }
}
        
        