# include <algorithm>
# include <iostream>
# include <sstream>
# include <string>
# include <vector>
# include <queue>
# include <set>
# include <map>
# include <cstdio>
# include <cstdlib>
# include <cctype>
# include <cmath>
# include <iomanip>
using namespace std;

//BEGINCUT
#define ARRSIZE(x) (sizeof(x)/sizeof(x[0]))

template<typename T> void print( T a )
{
    cerr << a;
}

static void print( double a )
{
    cerr << setprecision(6) << a;
}

static void print( long long a )
{
    cerr << a << "L";
}

static void print( string a )
{
    cerr << '"' << a << '"';
}

template<typename T> void print( string str, vector<T> a )
{
    cerr << str << " : {";
    for ( int i = 0 ; i < a.size() ; i++ )
    {
        if ( i != 0 ) cerr << ", ";
        print( a[i] );
    }
    cerr << "}" << endl;
}

template<typename T> bool eq( int n, T have, T need, bool printOnScreen = true )
{
    bool result = (have == need);
    if ( printOnScreen )
    {
        if ( result )
        {
            cerr << "Case " << n << " passed." << endl;
        }
        else
        {
            cerr << "Case " << n << " failed." << endl;
            cerr << "Returned : ";
            print( have );
            cerr << endl;
            cerr << "Expected : ";
            print( need );
            cerr << endl << endl;
        }
    }
    return result;
}

template<typename T> void eq( int n, vector<T> have, vector<T> need )
{
    bool result = (have.size() == need.size());
    if( !result )
    {
        cerr << "Case " << n << " failed: Returned " << have.size() << " elements; Expected " 
            << need.size() << " elements." << endl;
        print( "Returned", have );
        print( "Expected", need );
        cerr << endl;
        return;
    }
    for( int i = 0; i < have.size(); i++ )
    {
        if( !eq( n, have[i], need[i], false ) )
        {
            cerr << "Case " << n << " failed. Expected and Returned array differ in position " 
                << i << "." << endl;
            print( "Returned", have );
            print( "Expected", need );
            cerr << endl;
            return;
        }
    }
    cerr << "Case " << n << " passed." << endl;
}

static bool eq( int n, double have, double need, bool printOnScreen = true )
{
    bool result = ((have - need > 0 ? have - need : -have + need) < 1E-9 ||
        (have > (1 - 1E-9 * (need > 0 ? 1 : -1)) * need &&
        have < (1 + 1E-9 * (need > 0 ? 1 : -1)) * need));
    if ( printOnScreen )
    {
        if ( result )
        {
            cerr << "Case " << n << " passed." << endl;
        }
        else
        {
            cerr << "Case " << n << " failed." << endl;
            cerr << "Returned : ";
            print( have );
            cerr << endl;
            cerr << "Expected : ";
            print( need );
            cerr << endl << endl;
        }
    }
    return result;
}
//ENDCUT

class $CLASSNAME$
{
public:
    $METHODSIGNATURE$
    {
        $RC$ res;
        return res;
    }
};

//BEGINCUT
int main( int argc, char* argv[] )
{
    $TESTCASES$
}
//ENDCUT

// Powered by thefourtheyeEditor
