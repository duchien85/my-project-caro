<html>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>        
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />	        
		<title>Thống kê CCU của myPlay</title>		
    </head>
	
	<body>
		<h1 align="center"> Bảng thống kê CCU của myPlay </h1>						
		<?php			
			function getCCU($url){
				$homepage = file_get_contents($url);			
				//echo $homepage.'<br>';
				$json = substr($homepage, 3);										
				$obj = json_decode($json, true);			
				return $obj["servers"][0]["numUsers"];
			}			
			//$root = "http://myplay.apps.zing.vn/caro/service.php?act=getServerInfo";
			$root = "http://120.138.65.104";
			$arr = array(													
						//"Thu" => "http://74.125.71.99",
						"Caro" => "http://myplay.apps.zing.vn/caro/service.php?act=getServerInfo", 
						 "Cá Ngựa" => "http://myplay.apps.zing.vn/ccn/service.php?act=getServerInfo",						 
						 "Cờ Tướng" => "http://myplay.apps.zing.vn/cotuong/service.php?act=getServerInfo",
						 "Tá Lả" => "http://myplay.apps.zing.vn/tala/service.php?act=getServerInfo",
						 "Tiến Lên" => "http://myplay.apps.zing.vn/tienlen/service.php?act=getServerInfo",
						 "Poker HK" => "http://myplay.apps.zing.vn/pokerhk/service.php?act=getServerInfo",
						 "Poker US" => "http://myplay.apps.zing.vn/pokerus/service.php?act=getServerInfo",
						 "Bida" =>"http://myplay.apps.zing.vn/bida/service.php?act=getServerInfo",
						 "Bida Card" =>"http://myplay.apps.zing.vn/bidacard/service.php?act=getServerInfo",
						 "Black Jack" =>"http://myplay.apps.zing.vn/xidzach/service.php?act=getServerInfo",
						 "Binh Xập Xám" =>"http://myplay.apps.zing.vn/binh/service.php?act=getServerInfo"
						);
			
			$kq = array();
			$sapxep = array();
			
			foreach ($arr as $game => $url){
				$kq[$game] = getCCU($url);			
				$sapxep[] = $game;
			}
			
			$length = count($sapxep);			
			for ($i = 0; $i < $length - 1; $i++)
				for ($j = $i + 1; $j < $length; $j++){					
					if ($kq[$sapxep[$i]] < $kq[$sapxep[$j]]){
						$tmp = $sapxep[$i];
						$sapxep[$i] = $sapxep[$j];
						$sapxep[$j] = $tmp;
					}
				}		


			echo '<table border="1" width="275" align="center">';
			echo '<th width="50">Rank</th>';			
			echo '<th width="150">Game</th>';
			echo '<th>CCU</th>';
			$total = 0;
			for ($i = 0; $i < $length ; $i++){						
				$game = $sapxep[$i]; 
				$rank = $i + 1;
				echo '<tr>';
				if ($rank <= 3){
					echo '<td align="center"><b><font color="red">'.$rank.'</font></b></td>';
					echo  '<td><a style="color:red; font-weight:bold;" href="'.$arr[$game].'">'.$game.'</a></td>';
					echo  '<td align="right" style="color:red;">'.$kq[$game].'</td>';
				}
				else{
					echo '<td align="center">'.$rank.'</td>';				
					echo  '<td><a style="color:black;" href="'.$arr[$game].'">'.$game.'</a></td>';
					echo  '<td align="right" style="color:black;">'.$kq[$game].'</td>';
				}
				
				echo '</tr>';
				
				$total = $total + $kq[$game];				
			}			
			echo '<td></td>';
			echo '<td><b>TOTAL</b></td>';
			echo '<td align="right"><b>'.$total.'</b></td>';
			echo '</table>';
			
		?>
	</body>

</html>