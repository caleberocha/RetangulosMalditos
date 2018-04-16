param(
    [Parameter(Mandatory=$true)]$Count,
    [Parameter(Mandatory=$true)]$maxCoord,
    [Parameter(Mandatory=$true)]$OutFile,
    [switch]$Force
)

if(!$Force -and (Test-Path $OutFile)) {
    if((Read-Host "O arquivo já existe! Deseja sobreescrevê-lo? [S/N]") -ne "s") {
        return;
    }
}

$colors = "amarelo","azul-claro","azul-escuro","cinza","dourado","laranja","marrom","preto","verde-claro","verde-escuro","vermelho","violeta"
[int]$maxCoord1 = $maxCoord * 0.8
Write-Output $Count | Out-File $OutFile
1..$count | % {
    $r = Get-Random -Minimum 1 -Maximum $maxCoord1;
    $s = Get-Random -Minimum 1 -Maximum $maxCoord1;
    Write-Output "$r $s $(Get-Random -Minimum $r -Maximum $maxCoord) $(Get-Random -Minimum $s -Maximum $maxCoord) $($colors | Get-Random)"} | Out-File $OutFile -Append