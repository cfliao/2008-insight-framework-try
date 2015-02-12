package s2h.sysutils.hmbs;

class PWDCommand extends ConsoleCommand
{
    private static final String NAME = "pwd";

    private static final String DESC = "used to print the current directory.";

    private static final String HELP = "- pwd";

    public PWDCommand()
    {
        super(NAME, DESC, HELP);
    }

    public void execute(String[] args) throws CommandFailedException
    {
        Console.out.println(super.getCurrentDir().getAbsolutePath());
    }

    protected void initialize() throws Exception
    {
    }

    protected boolean needsMQ()
    {
        return false;
    }
}