thefourtheyeEditor [![Build Status](https://secure.travis-ci.org/thefourtheye/thefourtheyeEditor.png)](https://travis-ci.org/thefourtheye/thefourtheyeEditor)
==================

This is a very lightweight plugin for Topcoder Arena to participate in Single Round Matches, which can build testcases and lets the users to store the solutions as local files, so that any editor or IDE can be used to edit them. It also maintains the solutions in the directories named as the SRM's display name.

 1. [Installation](#installation)
 2. [Configuration](#configuration)

Installation
------------

Installation is very simple and straight forward. The steps are explained here 
<a href="http://www.thefourtheye.in/2013/09/installing-thefourtheyeeditor.html" target="_blank">http://www.thefourtheye.in/2013/09/installing-thefourtheyeeditor.html</a>

Configuration
-------------

All the configuration will be done in `contestapplet.conf`, which can be found in user's home directory. As of now, there are only 7 configuration parameters available.

```
thefourtheyeEditor.replaceSolutionIfAlreadyExists
thefourtheyeEditor.SolutionsDirectory
thefourtheyeEditor.Templates.C++
thefourtheyeEditor.Templates.Java
thefourtheyeEditor.Templates.Python
thefourtheyeEditor.Templates.VB
thefourtheyeEditor.Templates.C\#
```

**thefourtheyeEditor.replaceSolutionIfAlreadyExists**

If this value is `yes`, then the solution will be overwritten, if it already exists.

*Example:*

```
thefourtheyeEditor.replaceSolutionIfAlreadyExists=yes
```

**thefourtheyeEditor.SolutionsDirectory**

This is the directory in which the solutions will be stored.

*Example:*

```
thefourtheyeEditor.SolutionsDirectory=/home/thefourtheye/MyDen/TopCoder/Solutions/
```

**thefourtheyeEditor.Templates.C++**

This is the C++ Template file which will be used by the thefourtheyeEditor to produce the solution template and sample testcases.
You can find the sample C++ template file [here](https://raw.github.com/thefourtheye/thefourtheyeEditor/master/templates/C++Template.cpp).

*Example:*

```
thefourtheyeEditor.Templates.C++=/home/thefourtheye/templates/C++Template.cpp
```

**thefourtheyeEditor.Templates.Java**

This is the Java Template file which will be used by the thefourtheyeEditor to produce the solution template and sample testcases.
You can find the sample Java template file [here](https://raw.github.com/thefourtheye/thefourtheyeEditor/master/templates/JavaTemplate.java).

*Example:*

```
thefourtheyeEditor.Templates.Java=/home/thefourtheye/templates/JavaTemplate.java
```

**thefourtheyeEditor.Templates.Python**

This is the Python Template file which will be used by the thefourtheyeEditor to produce the solution template and sample testcases.
You can find the sample Python template file [here](https://raw.github.com/thefourtheye/thefourtheyeEditor/master/templates/PythonTemplate.py).

*Example:*

```
thefourtheyeEditor.Templates.Python=/home/thefourtheye/templates/PythonTemplate.py
```

**thefourtheyeEditor.Templates.VB**

This is the Visual Basic Template file which will be used by the thefourtheyeEditor to produce the solution template and sample testcases.
You can find the sample Visual Basic template file [here](https://raw.github.com/thefourtheye/thefourtheyeEditor/master/templates/VBTemplate.vb).

*Example:*

```
thefourtheyeEditor.Templates.VB=/home/thefourtheye/templates/VBTemplate.vb
```

**thefourtheyeEditor.Templates.C\#**

This is the C# Template file which will be used by the thefourtheyeEditor to produce the solution template and sample testcases.
You can find the sample C# template file [here](https://raw.github.com/thefourtheye/thefourtheyeEditor/master/templates/CSharpTemplate.cs).

*Example:*

```
thefourtheyeEditor.Templates.C\#=/home/thefourtheye/templates/CSharpTemplate.cs
```
