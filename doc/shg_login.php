<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=big5">
</head>
<form name="MyForm" method="POST" action="http://140.110.17.137/video_conference/shg.php">
<table>
<!-- tr>
  <td>Sender Key<td>0cf175b9c0f1b6a831c399e26978a631<br>
</tr>
<tr>
  <td>Receiver Key<td>p029hb920ldhya9aiac399e68728a8h3<br>
</tr -->
<?
$d=getdate();
$timestamp=sprintf("%d%02d%02d%02d%02d%02d",$d['year'],$d['mon'],$d['mday'],$d['hours'],$d['minutes'],$d['seconds']); 
?>
<input type="hidden" name="Key" value=<?echo $timestamp?> disable><br>
<?
$Check_Sum=md5("0cf175b9c0f1b6a831c399e26978a631"."p029hb920ldhya9aiac399e68728a8h3".$timestamp);
?>
<input type="hidden" size=30 name="Check_Sum" value="<?echo $Check_Sum?>">
<tr>
    <td>�|ĳ�N�X</td>
    <td><input type=text name=meeting_id value=100></td>
  </tr>
  <tr>
    <td>�|ĳ�D�D</td>
    <td><input type=text name=meeting_title value="video test"</td>
  </tr>
  <tr>
    <td>�D�u�b��</td>
     <td><input type=text name=meeting_owner_id value="c00jhw00"></td>
  </tr>
  <tr>
    <td>�D�u�W��</td>
    <td><input type=text name=meeting_owner_name value="�d�Ӫl"></td>
  </tr>
  <tr>
    <td>�n�J�b��</td>
    <td><input type=text name=meeting_visitor_id value="c00jhw00"></td>
  </tr>
  <tr>
    <td>�m�W</td>
    <td><input type=text name=meeting_visitor_name value="�d�Ӫl"></td>
  </tr>
<tr>
<td colspan=2><input type="submit" value="Submit">
</tr>
</table>
</form>
</html>

