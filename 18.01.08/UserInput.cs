using System;

public class TextInput
{
    protected string content = "";
    public virtual void Add(char c)
    {
        content += c;
    }
    public string GetValue()
    {
        return content;
    }
    public TextInput() { }
    public TextInput(string input)
    {
        content = input;
    }
}

public class NumericInput : TextInput
{
    public override void Add(char c)
    {
        if (c >= 48 && c <= 57)
        {
            content += c;
        }
        else { };
    }
}


public class UserInput
{
    public static void Main(string[] args)
    {
        TextInput input = new NumericInput();
        input.Add('1');
        input.Add('a');
        input.Add('0');
        Console.WriteLine(input.GetValue());
    }
}
