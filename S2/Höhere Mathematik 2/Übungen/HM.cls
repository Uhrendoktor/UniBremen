\NeedsTeXFormat{LaTeX2e}
\ProvidesClass{HM}[2021/10/01 HM1 class]

\LoadClass[12pt,letterpaper]{article}
\RequirePackage{fullpage, fancyhdr, amsfonts, amsmath, amssymb, mathtools, polynom, gauss, xparse, longtable, graphicx, tikz}

\DeclarePairedDelimiter\ceil{\lceil}{\rceil}
\DeclarePairedDelimiter\floor{\lfloor}{\rfloor}

% Edit these as appropriate           
\newcommand\NetIDa{6113829}           % <-- NetID of person #1
\newcommand\NetIDb{6111554}           % <-- Matrikelnummer of person #2 (Comment this line out for problem sets)

\newcommand{\arccosh}{\text{arccosh}}
\newcommand{\Eig}{\text{Eig}}
\newcommand{\Span}{\text{Span}}
\newcommand{\feq}{\overset{!}{=}}
%custom math commands
\NewDocumentCommand{\m}{ m o }{\begin{gmatrix}[p]#1 \IfValueT{#2}{\rowops#2}\end{gmatrix}}
\NewDocumentCommand{\md}{ m o }{\begin{gmatrix}[v]#1 \IfValueT{#2}{\rowops#2}\end{gmatrix}}

\newenvironment{eqnn}{
	\NewDocumentCommand{\eqn}{O{} O{\Leftrightarrow} m m O{}}{\geqn[##1][##2]{##3}{=}{##4}[##5]}
	\NewDocumentCommand{\eqnf}{O{} m m O{}}{\eqn[##1][]{##2}{##3}[##4]}
	\NewDocumentCommand{\geqn}{O{} O{\Leftrightarrow} m m m O{}}{##1&$##2$&$##3$&$##4$&$##5$&\def\temp{##6}\ifx\temp\empty$ $\else$\vert$ ##6\fi\\}
	\NewDocumentCommand{\geqnf}{O{} m m m O{}}{\geqn[##1][]{##2}{##3}{##4}[##5]}
	\newcommand{\eqntext}[1]{\multicolumn{6}{l}{\rule{0pt}{17.5pt}\parbox{\textwidth-\leftmargin-1cm}{##1}}\\[5pt]}
	\newcommand{\eqnspace}{\\[0pt]}

	\setlength\LTleft{\leftmargin + 1cm}
\setlength\LTright{\fill}
	\begin{longtable}{c c r c l l}
}{
	\end{longtable}
}
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