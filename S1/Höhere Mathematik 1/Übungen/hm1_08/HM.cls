\NeedsTeXFormat{LaTeX2e}
\ProvidesClass{HM}[2021/10/01 HM1 class]

\LoadClass[12pt,letterpaper]{article}
\RequirePackage{fullpage, fancyhdr, amsfonts, amsmath, amssymb, mathtools, polynom}

% Edit these as appropriate
\newcommand\course{HM 1}
\newcommand\hwnumber{8}                  % <-- homework number
\newcommand\NetIDa{6113829}           % <-- NetID of person #1
\newcommand\NetIDb{6111554}           % <-- Matrikelnummer of person #2 (Comment this line out for problem sets)

%custom math commands
\newcommand{\alignleft}[1]{\tag*{\llap{\makebox[\linewidth][l]{$#1$}}}}
\newcommand{\eqinfo}[1]{&\makebox[2cm][l]{$\vert #1$}}
\newcommand{\LLeftrightarrow}{ \alignleft{\Leftrightarrow}}

\newcommand{\Eqn}[3]{#1 &#2 #3}
\newcommand{\eqnf}[2]{\Eqn{#1}{=}{#2}\\}
\newcommand{\eqn}[2]{\LLeftrightarrow\Eqn{#1}{=}{#2}\\}
\newcommand{\eqni}[3]{\LLeftrightarrow\Eqn{#1}{=}{#2}\eqinfo{#3}\\}
\newcommand{\eqnfi}[3]{\Eqn{#1}{=}{#2}\eqinfo{#3}\\}
\newcommand{\geqnf}[3]{\Eqn{#1}{#2}{#3}\\}
\newcommand{\geqn}[3]{\LLeftrightarrow\Eqn{#1}{#2}{#3}\\}
\newcommand{\geqni}[4]{\LLeftrightarrow\Eqn{#1}{#2}{#3}\eqinfo{#4}\\}
\newcommand{\geqnfi}[4]{\Eqn{#1}{#2}{#3}\eqinfo{#4}\\}

%custom MATH Symbols
\newcommand{\R}{\mathbb{R}}
\newcommand{\N}{\mathbb{N}}
\newcommand{\Z}{\mathbb{Z}}
\newcommand{\Q}{\mathbb{Q}}
\newcommand{\C}{\mathbb{C}}
%end custom math commands

\pagestyle{fancyplain}
\headheight 35pt
\lhead{\NetIDa}
\lhead{\NetIDa\\\NetIDb}                 % <-- Comment this line out for problem sets (make sure you are person #1)
\chead{\textbf{\Large Übung \hwnumber}}
\rhead{\course \\ \today}
\lfoot{}
\cfoot{}
\rfoot{\small\thepage}
\headsep 1.5em