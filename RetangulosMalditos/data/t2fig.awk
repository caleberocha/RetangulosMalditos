#!/usr/bin/awk -f

####### Conversor para o xfig. Uso:
#
# t2fig.awk < arq.txt > arq.fig 
#
#    onde arq.txt é um arquivo de entrada no formato do trabalho

{ if ( NF == 5 ) {
    currfillcolor = idx[$5];
    box( $1, -$2, $3, -$4 );
  }
}

BEGIN {
  print "#FIG 3.2\nLandscape\nCenter\nMetric\nA4\n100.00\nSingle\n-2\n1200 2\n";
  currfillcolor = 0;

  color["verde-claro"] = "70f070";
  color["vermelho"] = "e06000";
  color["azul-claro"] = "00cfff";
  color["amarelo"] = "ffff30";
  color["verde-escuro"] = "30a030";
  color["marrom"] = "960000";
  color["azul-escuro"] = "0000ff";
  color["cinza"] = "808080";
  color["dourado"] = "ffd700";
  color["violeta"] = "b600b6";
  color["preto"] = "000000";
  color["laranja"] = "ffa030";

  c = 32;
  for( i in color ) {
    print "0 " c " #" color[i];
    idx[i] = c;
    c++;
  }
  print "";
}

######################################
function box( x1, y1, x2, y2 ) {
  x1 *= 20;
  y1 *= 20;
  x2 *= 20;
  y2 *= 20;
  print "2 1 0 1 0", currfillcolor, "50 0 20 0.000 0 2 0 0 0 5\n\t" x1, y1, x1, y2, x2, y2, x2, y1, x1, y1;
}
