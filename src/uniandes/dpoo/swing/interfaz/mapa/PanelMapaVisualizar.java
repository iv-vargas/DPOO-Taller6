package uniandes.dpoo.swing.interfaz.mapa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import uniandes.dpoo.swing.mundo.Restaurante;

@SuppressWarnings("serial")
public class PanelMapaVisualizar extends JPanel
{
    /**
     * La etiqueta donde se dibuja el mapa y se hacen las señales de los restaurantes
     */
    private JLabel labMapa;

    /**
     * La lista de restaurantes que se están dibujando en el mapa
     */
    private List<Restaurante> restaurantes;

    public PanelMapaVisualizar( )
    {
        this.labMapa = new JLabel( new ImageIcon( "./imagenes/mapa.png" ) );
        labMapa.setBorder( new LineBorder( Color.DARK_GRAY ) );
        add( labMapa, BorderLayout.CENTER );
    }

    @Override
    public void paint( Graphics g )
    {
        super.paint( g );
        Graphics2D g2d = ( Graphics2D )g;

        if( restaurantes == null )
            return;

        // Las coordenadas de los restaurantes son relativas a la etiqueta del mapa
        int offsetX = labMapa.getX( );
        int offsetY = labMapa.getY( );

        g2d.setFont( new Font( "SansSerif", Font.BOLD, 12 ) );
        for( Restaurante r : restaurantes )
        {
            int x = offsetX + r.getX( );
            int y = offsetY + r.getY( );

            // Marca la ubicación del restaurante
            g2d.setColor( Color.RED );
            g2d.fillOval( x - 3, y - 3, 7, 7 );

            // Escribe el nombre del restaurante
            g2d.setColor( Color.BLACK );
            g2d.drawString( r.getNombre( ), x + 6, y + 4 );
        }
    }

    /**
     * Actualiza la lista de restaurantes y llama al método repaint() para que se actualice el mapa
     * @param nuevosRestaurantes
     */
    public void actualizarMapa( List<Restaurante> nuevosRestaurantes )
    {
        if( restaurantes != null )
        {
            this.restaurantes.clear( );
            this.restaurantes.addAll( nuevosRestaurantes );
        }
        else
        {
            this.restaurantes = nuevosRestaurantes;
        }
        repaint( );
    }
}
