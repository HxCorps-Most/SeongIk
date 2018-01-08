using System;

public class Palindrome
{
    public static bool isPalindrome(string word)
    {
        char[] wordR = word.ToCharArray();
        Array.Reverse(wordR);
        string wordRstr = new string(wordR);

        return string.Equals(word, wordRstr, StringComparison.CurrentCultureIgnoreCase);

        throw new NotImplementedException("Waiting to be implemented.");
    }

    public static void Main(string[] args)
    {
        try
        {
            Console.WriteLine(Palindrome.isPalindrome("Deleveled"));

        }
        catch (NotImplementedException)
        {
            Console.WriteLine("Not done yet");
        }
    }
}

